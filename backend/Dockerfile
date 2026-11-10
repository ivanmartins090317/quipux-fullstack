# Plano B na Railway: Settings → Builder = Dockerfile (Root Directory = backend).
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app
COPY . .
RUN chmod +x mvnw && ./mvnw -DskipTests clean package

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/prova-0.0.1-SNAPSHOT.jar app.jar
ENV SPRING_PROFILES_ACTIVE=prod
CMD ["sh", "-c", "java -Dserver.port=${PORT:-8080} -jar app.jar"]
