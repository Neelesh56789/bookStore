From openjdk:17-jdk-slim as build
WORKDIR /app
COPY /target/bookservice-0.0.1-SNAPSHOT.jar /app/bookservice.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/app/bookservice.jar"]