FROM maven:3.9-amazoncorretto-17

WORKDIR /app

# Expose ports
EXPOSE 8080 5005

# Copy the Maven project files
COPY pom.xml .
COPY .env .
COPY src ./src
