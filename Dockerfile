FROM eclipse-temurin:21-jdk-jammy

VOLUME /tmp
ARG JAR_FILE=target/*.jar

ENTRYPOINT["java", "-jar", "/app.jar"]