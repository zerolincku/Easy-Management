FROM maven:3.8.6-openjdk-11 AS build

COPY pom.xml /app/

COPY src /app/src/

WORKDIR /app/

RUN mvn clean package -Dmaven.test.skip=true -Pdev

FROM openjdk:11-jdk-slim

ARG JAR_FILE=management-*.jar

COPY --from=build /app/target/${JAR_FILE} /management/app.jar

WORKDIR /management

EXPOSE 8089

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms128m -Xmx256m"

ENTRYPOINT java $JAVA_OPTS -jar app.jar
