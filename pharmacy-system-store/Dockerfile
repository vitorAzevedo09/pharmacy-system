FROM openjdk:17-slim
ARG JAR_FILE=*.jar
COPY /target/pharmacy-system-store-0.0.1-SNAPSHOT.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]
