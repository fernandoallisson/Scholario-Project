# 1º Estágio - Build
FROM maven:3.8.5-eclipse-temurin-17 AS builder
LABEL authors="fernando-alisson"
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src .
RUN mvn clean package -DskipTests

# 2º Estágio - Running
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
