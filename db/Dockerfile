FROM mysql/mysql-server:8.0.31

MAINTAINER zerolinck@foxmail.com

ENV TZ=Asia/Shanghai

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY ./management.sql /docker-entrypoint-initdb.d
