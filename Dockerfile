# Use an OpenJDK runtime as a base image
FROM openjdk:17-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged JAR file into the container at the specified path
COPY target/pharmacy-system-store-0.0.1-SNAPSHOT.jar /app/pharmacy-system-store-0.0.1-SNAPSHOT.jar

# Copy the .env file into the container
COPY .env /app/.env

# Expose the port the app runs on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "pharmacy-system-store-0.0.1-SNAPSHOT.jar"]
