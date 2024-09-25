# 1º Estágio - Building
FROM eclipse-temurin:17-jdk-jammy  as builder
LABEL authors="fernando-alisson"
WORKDIR /app
COPY src .
RUN .mvnw clean package

# 2º Estágio - Running
FROM eclipse-temurim:17-jre-jammy
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/app.jar
EXPOSE 8080


ENTRYPOINT ["java", "-jar", "/app/app.jar"]