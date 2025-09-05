# Microservice Cuenta Movimientos

Este microservicio gestiona las cuentas y movimientos bancarios.

## Despliegue

1. Compila el proyecto:
   ```bash
   mvn clean package
   ```
2. Construye la imagen Docker:
   ```bash
   docker build -t microservice-cuenta-movimientos .
   ```
3. Usa Docker Compose para levantar todos los servicios:
   ```bash
   docker-compose up --build
   ```

## Perfiles
- `dev`: Crea y actualiza tablas en la base de datos.
- `prod`: No crea ni actualiza tablas.

## Endpoints principales
- `/v3/api-docs.yaml`: Especificación OpenAPI
- `/swagger-ui.html`: Documentación interactiva

## Variables importantes
- Conexión a SQL Server y Kafka configurada en `application.properties` y `application.yml`.
