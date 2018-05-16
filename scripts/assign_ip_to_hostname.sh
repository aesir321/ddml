#!/bin/bash

PASSWORD=mastergroup3
HOSTNAME=mastergroup3
IP_ADDRESS=$(ip addr | grep 'state UP' -A2 | tail -n1 | awk '{print $2}' | cut -f1 -d'/')
CMD="http://dyndns.lrz.de/cgi/update.py?hostname=${HOSTNAME}&user=${HOSTNAME}&pass=${PASSWORD}&domain=dyn.mwn.de&myip=${IP_ADDRESS}"
echo ${CMD}
info=$(curl "${CMD}")
echo $info
echo $info $(date) >> register.log
