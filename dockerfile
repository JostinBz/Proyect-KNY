# Usar una imagen base de Java
FROM openjdk:17-jdk-alpine

WORKDIR /app

# Copiar el JAR de la aplicación al contenedor
COPY target/knyapi-0.0.1-SNAPSHOT.jar kny-api.jar

# Definir el comando para ejecutar la aplicaWción
ENTRYPOINT ["java", "-jar", "kny-api.jar"]
