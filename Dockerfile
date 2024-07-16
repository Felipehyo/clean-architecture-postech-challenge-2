FROM maven:3.9.6-eclipse-temurin-17-alpine AS builder

WORKDIR /app

COPY pom.xml .
COPY src src

RUN mvn package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app

ARG JAR_FILE=/app/target/*.jar
COPY --from=builder ${JAR_FILE} application.jar

CMD ["java", "-jar", "application.jar"]