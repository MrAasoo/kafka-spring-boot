# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-17 AS build
# Set the working directory inside the container
WORKDIR /app
# Copy the project files to the container
COPY . /app
# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
# Replace 'your-app-name.jar' with your actual generated jar name
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]