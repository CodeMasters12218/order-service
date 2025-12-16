# ============================
#   Etapa 1: Build con Maven
# ============================
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copiamos todo el proyecto (dentro del build context)
COPY . .

# Ahora, dentro del contenedor, la estructura será:
# /app/
#   ├── common-dto/
#   └── order-service/

# Instalamos common-dto
RUN cd common-dto && mvn clean install -DskipTests

# Construimos order-service
RUN cd order-service && mvn clean package -DskipTests

# ============================
#   Etapa 2: Imagen final
# ============================
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copiamos el JAR generado de order-service
COPY --from=build /app/order-service/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
