#!/bin/sh 
# Ejercicio 1c: protegiendo la propia maquina (DROP por defecto)
echo -n "Aplicando Reglas de Firewall..."
 
# FLUSH de reglas 
iptables -F 
iptables -X 
iptables -Z 
iptables -t nat -F



# Establecemos politica por defecto 
iptables -P INPUT DROP 
iptables -P OUTPUT DROP 
iptables -P FORWARD DROP 


# Empezamos a abrir, no a filtrar, porque ahora esta TODO denegado.
# Debemos decir de manera explícita qué es lo que queremos abrir:


# Operar en localhost sin limitaciones:
iptables -A INPUT -i lo -j ACCEPT
iptables -A OUTPUT -o lo -j ACCEPT

# A nuestra IP le dejamos todo:
iptables -A INPUT -s 192.168.2.1 -j ACCEPT
iptables -A OUTPUT -d 192.168.2.1 -j ACCEPT


# Este es el servicio que ofrece la maquina a internet, por tanto todo paquete
# entrante se acepta para ese puerto, y los salientes vinculados se aceptan: 
iptables -A INPUT -p tcp -m tcp --dport 80 -j ACCEPT
iptables -A OUTPUT -p tcp -m tcp --sport 80 -m state --state RELATED,ESTABLISHED -j ACCEPT

# Permitimos que la maquina pueda salir a la web:
iptables -A INPUT -p tcp -m tcp --sport 80 -m state --state RELATED,ESTABLISHED -j ACCEPT
iptables -A OUTPUT -p tcp -m tcp --dport 80 -j ACCEPT

# Y tambien a webs seguras:
iptables -A INPUT -p tcp -m tcp --sport 443 -m state --state RELATED,ESTABLISHED -j ACCEPT
iptables -A OUTPUT -p tcp -m tcp --dport 443 -j ACCEPT

# Permitimos la consulta a un primer DNS (DNS1 de google):
iptables -A INPUT -s 8.8.8.8 -p udp -m udp --sport 53 -j ACCEPT
iptables -A OUTPUT -d 8.8.8.8 -p udp -m udp --dport 53 -j ACCEPT

# Permitimos la consulta a un segundo DNS (DNS2 de google): 
iptables -A INPUT -s 8.8.4.4 -p udp -m udp --sport 53 -j ACCEPT 
iptables -A OUTPUT -d 8.8.4.4 -p udp -m udp --dport 53 -j ACCEPT

# Permitimos consultar el reloj de pool.ntp.org para sincronizarse: 
iptables -A INPUT -s 138.100.11.74 -p udp -m udp --dport 123 -j ACCEPT 
iptables -A OUTPUT -d 138.100.11.74 -p udp -m udp --sport 123 -j ACCEPT

# Barrera de backup por si cambiamos a modo ACCEPT temporalmente,
# con esto protegemos los puertos reservados y otros bien conocidos:
iptables -A INPUT -p tcp -m tcp --dport 3306 -j DROP 
iptables -A INPUT -p tcp -m tcp --dport 5432 -j DROP 
iptables -A INPUT -p tcp --dport 3306 -j DROP 
iptables -A INPUT -p tcp --dport 48675 -j DROP 
iptables -A INPUT -p tcp --dport 7175 -j DROP
iptables -A INPUT -p tcp --dport 43433 -j DROP 
iptables -A INPUT -p tcp --dport 48061 -j DROP

echo " OK . Verifique las reglas con: iptables -L -n"


#Añada las siguientes líneas al final del script anterior, para registrar los accesos no permitidos por las reglas de ACCEPT (genere tráfico que no haga saltar ninguna de las reglas anteriores para ver el efecto)

iptables -A INPUT -m limit --limit 3/s -j LOG --log-prefix "iptables_INPUT_denied: " --log-level 4
iptables -A FORWARD -m limit --limit 3/s -j LOG --log-prefix "iptables_FORWARD_denied: " --log-level 4
iptables -A OUTPUT -m limit --limit 3/s -j LOG --log-prefix "iptables_OUTPUT_denied: " --log-level 4

#El tráfico no permitido se registra en /var/log/syslog, puede verlo con el siguiente comando de forma interactiva:
# $ tail -f /var/log/syslog