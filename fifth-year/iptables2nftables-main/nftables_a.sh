#!/bin/sh 
echo -n "OJO: ejecutar el script con sudo: $ sudo ./script.sh\n"
echo -n "Aplicando Reglas de Firewall...\n"

# ***** FLUSH de todo ******
nft flush ruleset

# ***** Creando tablas ******
nft add table inet predeterminada
nft add table nat

# ***** Creando cadenas ******
nft 'add chain inet predeterminada input{ type filter hook input priority 0; policy accept; }'
nft 'add chain inet predeterminada output{ type filter hook output priority 0; policy accept; }'
nft 'add chain inet predeterminada forward{ type filter hook forward priority 0; policy accept; }'

nft 'add chain nat prerouting{ type nat hook prerouting priority 0; policy accept; }'
nft 'add chain nat postrouting{ type nat hook postrouting priority 0; policy accept; }'


# ***** Creando reglas ******

# El localhost se permite (por ejemplo conexiones locales a MySQL)
nft add rule inet predeterminada input iifname "lo" accept    
nft add rule inet predeterminada output oifname "lo" accept

# A nuestra IP le dejamos acceder a todo
nft add rule inet predeterminada input ip saddr 192.168.1.92 accept

# Al equipo 1 le dejamos entrar al MySQL para que mantenga la BBDD
nft add rule inet predeterminada input ip saddr 192.168.1.91 tcp dport 3306 accept

# El puerto 80 de www debe estar abierto, es un servidor web
nft add rule inet predeterminada input tcp dport 80 accept

# Y el resto, lo cerramos
nft add rule inet predeterminada input tcp dport 22 drop
nft add rule inet predeterminada input tcp dport 3306 drop



echo -n "Final del script. Verificacion:\n\n"
nft list ruleset

