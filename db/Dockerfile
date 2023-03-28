# Use the official MySQL Server 8.0.31 image 
FROM mysql/mysql-server:8.0.31

# Set the maintainer information for this Dockerfile
MAINTAINER zerolinck@foxmail.com

# Set the timezone to Shanghai
ENV TZ=Asia/Shanghai

# Link the timezone file to the local time and set the timezone
RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# Copy the management.sql script to the Docker image's entrypoint directory to be executed later
COPY ./management.sql /docker-entrypoint-initdb.d