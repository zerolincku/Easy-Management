# Use Maven to build the project
FROM maven:3.8.6-openjdk-11 AS build

# Copy the pom.xml file into the /app/ directory
COPY pom.xml /app/

# Copy the source code into the /app/src/ directory
COPY src /app/src/

# Set the working directory to the /app/ directory
WORKDIR /app/

# Build the project using Maven, skipping tests and using the "dev" profile
RUN mvn clean package -Dmaven.test.skip=true -Pdev

# Start with OpenJDK 11 slim image for smaller footprint
FROM openjdk:11-jdk-slim

# Set the JAR_FILE argument to the name of the JAR file produced by the Maven build
ARG JAR_FILE=management-*.jar

# Copy the JAR file from the build stage into the /management/app.jar file in this stage
COPY --from=build /app/target/${JAR_FILE} /management/app.jar

# Set the working directory to the /management/ directory
WORKDIR /management

# Expose port 8089 for external connections
EXPOSE 8089

# Set the timezone to Shanghai and set Java options
ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms128m -Xmx256m"

# Run the application using the Java command with the specified Java options
ENTRYPOINT java $JAVA_OPTS -jar app.jar