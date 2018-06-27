# Distributed Data Mining Lab 
This repository contains the code used during the 2018 Summer semester TUM Distributed Data Mining Lab.  The code consists of setup scripts written using ansible to automation the configuration of clusters based on Hadoop and Apache Spark systems.

## Setup steps with Ansible
### These steps relate to the configuration for this TUM project at the LRZ however with some small modifications they can be adjusted for your own cluster.

1. Register hostnames with LRZ
2. Update ip_hosts with the new ips assigned in Open Nebula
3. Update hosts with newly registered LRZ names
4. Run the following code: ansible-playbook ip.yml -i ip_hosts
5. Flush the DNS on your local machine (this may or may not be necessary on your machine).
7. Restart the machines
6. ansible-playbook ssh_master.yml -i hosts
7. ansible-playbook lrz_security.yml -i hosts
8. Restart the machines
9. Mount Datablock in new machines and add entries to fstab
10. ansible-playbook java.yml -i hosts
11. Update template/slaves with the registered LRZ addresses.
12. ansible-playbook hadoop.yml -i hosts
13. Format the namenode 
14. Disable firewall on all nodes
15. $HADOOP_HOME/sbin/start-dfs.sh
16. $HADOOP_HOME/sbin/start-yarn.sh
17. ansible-playbook spark.yml -i hosts
18. $SPARK_HOME/sbin/start-history-server.sh

## Word counting


## Prime counting
Several implementations for counting prime numbers have been implemented using Java and python and Hadoop MapReduce.  These implementations can be found under prime_counting.

## Ngrams
A small implementation using the MLlib to find Ngrams was implemented in python, but this was found to be unreliable.

## Kmers
Finally a scala program was written to identify kmers in .fasta files.
