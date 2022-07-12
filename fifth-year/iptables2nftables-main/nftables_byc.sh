#!/bin/sh 
echo -n "OJO: ejecutar el script con sudo: $ sudo ./script.sh\n"
echo -n "Aplicando Reglas de Firewall...\n"

# ***** FLUSH de todo ******
nft flush ruleset

# ***** Creando tablas ******
nft add table inet predeterminada

# ***** Creando cadenas ******
nft 'add chain inet predeterminada input{ type filter hook input priority 0; policy drop; }'
nft 'add chain inet predeterminada output{ type filter hook output priority 0; policy drop; }'
nft 'add chain inet predeterminada forward{ type filter hook forward priority 0; policy drop; }'


#******  Empezamos a abrir, no a filtrar, porque ahora esta TODO denegado. ******
# Debemos decir de manera explícita qué es lo que queremos abrir:

# Operar en localhost sin limitaciones:
nft add rule inet predeterminada input iifname "lo" accept    
nft add rule inet predeterminada output oifname "lo" accept

# A nuestra IP le dejamos acceder a todo
nft add rule inet predeterminada input ip saddr 192.168.1.92 accept
nft add rule inet predeterminada output ip daddr 192.168.1.92 accept

# Este es el servicio que ofrece la maquina a internet, por tanto todo paquete
# entrante se acepta para ese puerto, y los salientes vinculados se aceptan: 
nft add rule inet predeterminada input tcp dport 80 accept
nft add rule inet predeterminada output tcp sport 80 ct state related,established accept

# Permitimos que la maquina pueda salir a la web:

nft add rule inet predeterminada input tcp sport 80 ct state related,established accept
nft add rule inet predeterminada output tcp dport 80 accept

# Y tambien a webs seguras:
nft add rule inet predeterminada input tcp sport 443 ct state related,established accept
nft add rule inet predeterminada output tcp dport 443 accept

# Permitimos la consulta a un primer DNS (DNS1 de google):
nft add rule inet predeterminada input ip saddr 8.8.8.8 udp sport 53 accept
nft add rule inet predeterminada output ip daddr 8.8.8.8 udp dport 53 accept

# Permitimos la consulta a un segundo DNS (DNS2 de google): 
nft add rule inet predeterminada input ip saddr 8.8.4.4 udp sport 53 accept
nft add rule inet predeterminada output ip daddr 8.8.4.4 udp dport 53 accept

# Permitimos consultar el reloj de pool.ntp.org para sincronizarse: 
nft add rule inet predeterminada input ip saddr 138.100.11.74 udp sport 123 accept
nft add rule inet predeterminada output ip daddr 138.100.11.74 udp dport 123 accept

# Barrera de backup por si cambiamos a modo ACCEPT temporalmente,
# con esto protegemos los puertos reservados y otros bien conocidos:
nft add rule inet predeterminada input tcp dport 3306 drop
nft add rule inet predeterminada input tcp dport 5432 drop
nft add rule inet predeterminada input tcp dport 48675 drop
nft add rule inet predeterminada input tcp dport 7175 drop
nft add rule inet predeterminada input tcp dport 43433 drop
nft add rule inet predeterminada input tcp dport 48061 drop

echo -n "Final del script. Verificacion:\n\n"
nft list ruleset


#Añada las siguientes líneas al final del script anterior, para registrar los accesos no permitidos por las reglas de ACCEPT (genere tráfico que no haga saltar ninguna de las reglas anteriores para ver el efecto)

nft add rule inet predeterminada input log flags all log prefix "nftables_INPUT_denied"
nft add rule inet predeterminada output log flags all log prefix "nftables_OUTPUT_denied"
nft add rule inet predeterminada forward log flags all log prefix "nftables_FORWARD_denied"

#El tráfico no permitido se registra en /var/log/syslog, puede verlo con el siguiente comando de forma interactiva:
# $ tail -f /var/log/syslog
