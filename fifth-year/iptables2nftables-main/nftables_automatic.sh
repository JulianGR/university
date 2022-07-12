#!/bin/sh 

# Translated by iptables-restore-translate v1.8.7 on Sat Jan  1 15:03:18 2022
add table ip filter
add chain ip filter INPUT { type filter hook input priority 0; policy drop; }
add chain ip filter FORWARD { type filter hook forward priority 0; policy drop; }
add chain ip filter OUTPUT { type filter hook output priority 0; policy drop; }
add rule ip filter INPUT iifname "lo" counter accept
add rule ip filter INPUT ip saddr 192.168.1.92 counter accept
add rule ip filter INPUT tcp dport 80 counter accept
add rule ip filter INPUT tcp sport 80 ct state related,established  counter accept
add rule ip filter INPUT tcp sport 443 ct state related,established  counter accept
add rule ip filter INPUT ip saddr 8.8.8.8 udp sport 53 counter accept
add rule ip filter INPUT ip saddr 8.8.4.4 udp sport 53 counter accept
add rule ip filter INPUT ip saddr 138.100.11.74 udp dport 123 counter accept
add rule ip filter INPUT tcp dport 3306 counter drop
add rule ip filter INPUT tcp dport 5432 counter drop
add rule ip filter INPUT tcp dport 3306 counter drop
add rule ip filter INPUT tcp dport 48675 counter drop
add rule ip filter INPUT tcp dport 7175 counter drop
add rule ip filter INPUT tcp dport 43433 counter drop
add rule ip filter INPUT tcp dport 48061 counter drop
add rule ip filter INPUT limit rate 3/second burst 5 packets counter log prefix "iptables_INPUT_denied"
add rule ip filter FORWARD limit rate 3/second burst 5 packets counter log prefix "iptables_FORWARD_denied"
add rule ip filter OUTPUT oifname "lo" counter accept
add rule ip filter OUTPUT ip daddr 192.168.1.92 counter accept
add rule ip filter OUTPUT tcp sport 80 ct state related,established  counter accept
add rule ip filter OUTPUT tcp dport 80 counter accept
add rule ip filter OUTPUT tcp dport 443 counter accept
add rule ip filter OUTPUT ip daddr 8.8.8.8 udp dport 53 counter accept
add rule ip filter OUTPUT ip daddr 8.8.4.4 udp dport 53 counter accept
add rule ip filter OUTPUT ip daddr 138.100.11.74 udp sport 123 counter accept
add rule ip filter OUTPUT limit rate 3/second burst 5 packets counter log prefix "iptables_OUTPUT_denied"
# Completed on Sat Jan  1 15:03:18 2022