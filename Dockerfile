FROM liferay/jdk11:5.0.11-20230215153115

RUN mkdir -p /management

WORKDIR /management

ARG JAR_FILE=target/management-*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8089

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms128m -Xmx256m"

ENTRYPOINT java $JAVA_OPTS -jar app.jar
