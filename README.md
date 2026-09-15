# wishstore-history-service

## 1. Objetivo

Microservicio responsable exclusivamente de **registrar, consultar y eliminar** eventos del historial de acciones sobre una wishlist (agregar, eliminar y actualizar productos en favoritos).

Este microservicio **no** administra productos, wishlists ni usuarios, **no** implementa autenticación/seguridad, y **no** consume otros microservicios.

## 2. Tecnologías

- Java 21
- Spring Boot 3.x
- Maven
- MySQL
- Spring Data JPA
- Spring Validation
- Lombok
- Spring Web
- Spring Boot DevTools
- Docker

## 3. Arquitectura
Cliente
↓
HistoryController
↓
HistoryService
↓
HistoryServiceImpl
↓
HistoryRepository + HistoryMapper
↓
MySQL


## 5. Requisitos previos

- Java 21 instalado
- Maven (o usar el wrapper `./mvnw` incluido)
- MySQL 8.x corriendo localmente o accesible por red
- Docker (opcional, para ejecución en contenedor)
- Postman (opcional, para probar la API)

## 6. Configuración de MySQL

Ejecutar el script `wishstore_history_db.sql` en tu servidor MySQL:

```bash
mysql -u root -p < wishstore_history_db.sql
```

Esto crea la base de datos `wishstore_history_db` y la tabla `history`, con algunos registros de prueba.

## 7. Variables de entorno

| Variable | Descripción | Valor por defecto |
|---|---|---|
| `SERVER_PORT` | Puerto en el que corre el microservicio | `8083` |
| `DB_URL` | URL de conexión JDBC a MySQL | `jdbc:mysql://localhost:3306/wishstore_history_db` |
| `DB_USERNAME` | Usuario de MySQL | `root` |
| `DB_PASSWORD` | Contraseña de MySQL | `root` |

No hay contraseñas reales escritas en el código; los valores por defecto son solo para desarrollo local.

## 8. Cómo ejecutar localmente

```bash
export DB_URL=jdbc:mysql://localhost:3306/wishstore_history_db
export DB_USERNAME=root
export DB_PASSWORD=tu_password

./mvnw spring-boot:run
```

La aplicación quedará disponible en: `http://localhost:8083`

## 9. Cómo compilar con Maven

```bash
./mvnw clean package
```

Esto genera el JAR en `target/wishstore-history-service-0.0.1-SNAPSHOT.jar`.

## 10. Cómo ejecutar las pruebas

```bash
./mvnw clean test
```

## 11. Cómo construir la imagen Docker

```bash
docker build -t wishstore-history-service .
```

## 12. Cómo ejecutar el contenedor Docker

```bash
docker run -p 8083:8083 \
  -e DB_URL=jdbc:mysql://host.docker.internal:3306/wishstore_history_db \
  -e DB_USERNAME=root \
  -e DB_PASSWORD=tu_password \
  wishstore-history-service
```

> **Nota:** `host.docker.internal` permite que el contenedor acceda a un MySQL que corre en tu máquina local (funciona en Docker Desktop / Windows / Mac). En Linux puede ser necesario usar la IP del host o una red Docker compartida con el contenedor de MySQL. No se incluye Docker Compose en este proyecto porque está fuera del alcance definido.

## 13. Endpoints

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/history` | Listar todo el historial |
| GET | `/history/{id}` | Buscar un registro por ID |
| GET | `/history/wishlist/{wishlistId}` | Historial de una wishlist (ordenado por fecha desc.) |
| GET | `/history/product/{productId}` | Historial de un producto |
| POST | `/history` | Registrar un evento |
| DELETE | `/history/{id}` | Eliminar un registro |

## 14. Ejemplo de request (POST /history)

```json
{
  "wishlistId": 1,
  "productId": 10,
  "action": "ADD",
  "description": "Producto agregado a favoritos"
}
```

## 15. Ejemplo de respuesta (201 Created)

```json
{
  "id": 5,
  "wishlistId": 1,
  "productId": 10,
  "action": "ADD",
  "description": "Producto agregado a favoritos",
  "createdAt": "2026-09-13T10:30:00"
}
```

## 16. Ejemplo de respuesta de error (404 Not Found)

```json
{
  "timestamp": "2026-09-13T10:31:00",
  "status": 404,
  "error": "Not Found",
  "message": "No se encontró historial con id: 99999"
}
```

## 17. Cómo importar la colección Postman

1. Abrir Postman.
2. `File → Import`.
3. Seleccionar `postman/wishstore-history-service.postman_collection.json`.
4. La colección usa la variable `{{baseUrl}}` = `http://localhost:8083` por defecto.

## 18. Información para despliegue

- La aplicación lee toda la configuración de MySQL desde variables de entorno (`DB_URL`, `DB_USERNAME`, `DB_PASSWORD`), sin credenciales escritas en el código.
- El puerto es configurable vía `SERVER_PORT` (por defecto `8083`).
- El `Dockerfile` construye una imagen lista para producción con Java 21, usando un build multi-stage para mantener la imagen final liviana.
- No se incluye orquestación (Docker Compose, Kubernetes, etc.) porque está fuera del alcance de este microservicio.