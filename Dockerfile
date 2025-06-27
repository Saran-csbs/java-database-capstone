# Stage 1: Build the application
FROM maven:3.8.6-openjdk-17-slim AS builder
WORKDIR /app

# Copy only the files needed for dependency resolution
COPY pom.xml .
COPY src ./src

# Build the JAR without running tests
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copy the compiled JAR from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the default Spring Boot port (optional)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]
