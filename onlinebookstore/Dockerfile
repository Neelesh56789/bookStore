FROM openjdk:17-jdk-slim as build
WORKDIR /app
COPY target/onlinebookstore-0.0.1-SNAPSHOT.jar /app/onlinebookstore.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/onlinebookstore.jar"]