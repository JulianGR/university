#!/bin/sh 
echo -n "Aplicando Reglas de Firewall..."
 
# FLUSH de reglas 
iptables -F 
iptables -X 
iptables -Z 
iptables -t nat -F



# Establecemos politica por defecto 
iptables -P INPUT ACCEPT 
iptables -P OUTPUT ACCEPT 
iptables -P FORWARD ACCEPT 
iptables -t nat -P PREROUTING ACCEPT 
iptables -t nat -P POSTROUTING ACCEPT


# Empezamos a filtrar (reglas especificas): 


# El localhost se permite (por ejemplo conexiones locales a MySQL):
iptables -A INPUT -i lo -j ACCEPT

# A nuestra IP le dejamos acceder a todo:
iptables -A INPUT -s 192.168.2.1 -j ACCEPT

# Al equipo 1 le dejamos entrar al MySQL para que mantenga la BBDD
iptables -A INPUT -s 192.168.2.10 -p tcp --dport 3306 -j ACCEPT

# El puerto 80 de www debe estar abierto, es un servidor web:
iptables -A INPUT -p tcp --dport 80 -j ACCEPT

# Y el resto, lo cerramos:
iptables -A INPUT -p tcp --dport 3306 -j DROP
iptables -A INPUT -p tcp --dport 22 -j DROP


echo " OK . Verifique las reglas con: iptables -L -n"