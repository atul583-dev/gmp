# Use the latest Maven image with OpenJDK 17
FROM maven:3.8.8-eclipse-temurin-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml file and other Maven files to the container
COPY pom.xml ./

# Download all dependencies
RUN mvn dependency:go-offline

# Copy the entire project to the container
COPY . .

# Build the Spring Boot application (package the JAR)
RUN mvn clean package -DskipTests

# Use an official OpenJDK runtime image to run the application
FROM eclipse-temurin:17-jdk-jammy

# Set the working directory inside the runtime container
WORKDIR /app

# Copy the built Spring Boot JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the web application port (Spring Boot default is 8080)
EXPOSE 8080

# Command to run the Spring Boot web application
ENTRYPOINT ["java", "-jar", "app.jar"]
