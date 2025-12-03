# Step 1: Build the Spring Boot app
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

COPY . .

# Skip tests to speed up build and avoid environment issues
RUN mvn clean package -Dmaven.test.skip=true


# Step 2: Run the built JAR
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
