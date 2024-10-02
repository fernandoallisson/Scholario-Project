# 1º Estágio - Build
FROM maven:3.8.5-eclipse-temurin-17 AS builder
LABEL authors="fernando-alisson"
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Verificar o arquivos gerados
RUN ls -l /app/target

# 2º Estágio - Running
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]