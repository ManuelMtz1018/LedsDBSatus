# LedsDBStatus

## 🔦 Introducción

**LedsDBStatus** es una aplicación REST API desarrollada con **Spring Boot** que gestiona el estado y la información de lámparas (LEDs) almacenadas en una base de datos MySQL. Proporciona un sistema completo de CRUD (Crear, Leer, Actualizar, Eliminar) con validaciones, logging y manejo de errores integrados.

## 📋 ¿Qué es este proyecto?

LedsDBStatus es un microservicio backend que permite:
- **Consultar el estado** de todas las lámparas registradas en el sistema
- **Obtener información detallada** de una lámpara específica por ID
- **Crear nuevas lámparas** con validaciones automáticas
- **Actualizar el estado y descripción** de lámparas existentes
- **Eliminar lámparas** del sistema
- **Rastrear operaciones** mediante logging detallado de todas las acciones

## 🏗️ Estructura del Proyecto

```
LedsDBSatus/
├── src/
│   ├── main/
│   │   ├── java/org/example/ledsdbsatus/
│   │   │   ├── LedsDbSatusApplication.java        # Punto de entrada de la aplicación
│   │   │   ├── controller/
│   │   │   │   └── LedController.java             # Endpoints REST (/leds/*)
│   │   │   ├── model/
│   │   │   │   └── Lamp.java                      # Entidad JPA (tabla: lamps_status)
│   │   │   ├── repository/
│   │   │   │   └── LampRepository.java            # Acceso a datos (interfaz)
│   │   │   ├── services/
│   │   │   │   ├── LampService.java               # Interfaz de servicio
│   │   │   │   └── LampServiceImp.java            # Implementación del servicio
│   │   │   └── validations/
│   │   │       ├── IsRequired.java                # Anotación personalizada
│   │   │       └── RequiredValidation.java        # Lógica de validación
│   │   └── resources/
│   │       ├── application.properties             # Configuración de la aplicación
│   │       ├── static/                            # Archivos estáticos (vacío)
│   │       └── templates/                         # Plantillas (vacío)
│   └── test/
│       └── java/...                               # Tests unitarios
├── pom.xml                                        # Configuración de Maven
├── mvnw / mvnw.cmd                                # Maven Wrapper
└── README.md                                      # Este archivo
```

## 🔑 Componentes Principales

### **Controller (LedController)**
Define los endpoints REST disponibles:
- `GET /leds/all` - Obtiene todas las lámparas
- `GET /leds/status/{id}` - Obtiene una lámpara por ID
- `POST /leds/add` - Crea una nueva lámpara
- `PUT /leds/update/{id}` - Actualiza una lámpara existente
- `DELETE /leds/delete/{id}` - Elimina una lámpara

### **Model (Lamp)**
Representa una lámpara en el sistema con los siguientes atributos:
- `id` (Long) - Identificador único (generado automáticamente)
- `label` (String) - Nombre/etiqueta de la lámpara (requerido)
- `status` (boolean) - Estado actual (encendida/apagada)
- `description` (String) - Descripción adicional

### **Service (LampService)**
Capa de negocio que contiene la lógica de operaciones sobre lámparas:
- `findAll()` - Obtiene todas las lámparas
- `findById(Long id)` - Busca una lámpara por ID
- `save(Lamp lamp)` - Guarda una nueva lámpara
- `update(Long id, Lamp lamp)` - Actualiza una lámpara
- `delete(Long id)` - Elimina una lámpara

### **Repository (LampRepository)**
Interfaz que extiende `JpaRepository` y proporciona operaciones de acceso a datos de forma automática.

### **Validations**
Validación personalizada que asegura que el campo `label` sea obligatorio mediante la anotación `@IsRequired`.

## 🚀 Requisitos Previos

- **Java 17** o superior
- **Maven 3.6+** (o usar el Maven Wrapper incluido)
- **MySQL 8.0+** con una base de datos configurada
- **Git** (opcional, para clonar el repositorio)

## 📦 Instalación

### 1. Clonar o descargar el proyecto
```bash
cd C:\SWDev\Java\LedsDBSatus
```

### 2. Configurar la base de datos
Edita el archivo `src/main/resources/application.properties` con tus credenciales de MySQL:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/leds_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Instalar dependencias y compilar
```powershell
cd C:\SWDev\Java\LedsDBSatus
.\mvnw.cmd clean install
```

## ▶️ Ejecución

### Iniciar la aplicación
```powershell
cd C:\SWDev\Java\LedsDBSatus
.\mvnw.cmd spring-boot:run
```

La aplicación estará disponible en: `http://localhost:8080`

### Ejecutar tests
```powershell
cd C:\SWDev\Java\LedsDBSatus
.\mvnw.cmd test
```

## 📡 Uso de Endpoints

### Obtener todas las lámparas
```bash
curl -X GET http://localhost:8080/leds/all
```

### Obtener una lámpara por ID
```bash
curl -X GET http://localhost:8080/leds/status/1
```

### Crear una nueva lámpara
```bash
curl -X POST http://localhost:8080/leds/add \
  -H "Content-Type: application/json" \
  -d '{
    "label": "Lámpara Sala",
    "status": true,
    "description": "Lámpara principal de la sala de estar"
  }'
```

### Actualizar una lámpara
```bash
curl -X PUT http://localhost:8080/leds/update/1 \
  -H "Content-Type: application/json" \
  -d '{
    "label": "Lámpara Sala",
    "status": false,
    "description": "Lámpara apagada"
  }'
```

### Eliminar una lámpara
```bash
curl -X DELETE http://localhost:8080/leds/delete/1
```

## 📊 Logging

La aplicación incluye logging automático en todos los endpoints usando SLF4J:
- ℹ️ **INFO**: Operaciones exitosas (recuperación, creación, actualización, eliminación)
- ⚠️ **WARN**: Operaciones fallidas (ejemplo: no se pudo crear una lámpara)
- Los logs muestran IDs de recursos, estados y cambios realizados

Ejemplo de logs en consola:
```
INFO : Retrieved 5 lamps
INFO : Retrieved lamp with id 1: Lamp{id=1, label='Lámpara Sala', status=true, description='...'}
INFO : Adding new lamp: Lamp{label='Nueva Lámpara', status=true, description='...'}
```

## 🛠️ Tecnologías Utilizadas

- **Spring Boot 4.1.0** - Framework principal
- **Spring Data JPA** - Acceso a datos
- **MySQL Connector** - Driver de base de datos
- **Jakarta Validation** - Validación de datos
- **SLF4J** - Logging
- **Maven** - Gestión de dependencias

## 📝 Anotaciones Personalizadas

### `@IsRequired`
Valida que un campo sea obligatorio:
```java
@IsRequired
private String label;
```

## 🐛 Troubleshooting

### Error: conexión a base de datos rechazada
- Verifica que MySQL esté corriendo
- Comprueba las credenciales en `application.properties`
- Asegúrate de que la base de datos existe

### Error: puerto 8080 ya en uso
Cambia el puerto en `application.properties`:
```properties
server.port=8081
```

### Lámpara no encontrada (404)
Verifica que el ID existe en la base de datos:
```bash
curl -X GET http://localhost:8080/leds/all
```

## 📄 Licencia

Este proyecto es de uso privado.

## 👤 Autor

Desarrollado como parte de un sistema de gestión de dispositivos IoT.

---

**Última actualización:** Junio 2026

