# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the pre-built JAR file into the container
COPY target/inventory-service-1.0.0.jar /app/inventory-service.jar

# Expose the port the application will run on
EXPOSE 8082

# Run the application
CMD ["java", "-jar", "inventory-service.jar"]
