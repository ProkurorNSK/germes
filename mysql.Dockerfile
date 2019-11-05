FROM mysql:8.0.18

LABEL Author="ProkurorNSK", Version=0.1

ENV MYSQL_ROOT_PASSWORD root

RUN service mysql start
RUN mysql
#&& \
#    mysql -u root -p${MYSQL_ROOT_PASSWORD} -e "CREATE DATABASE germes"