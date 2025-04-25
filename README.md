# Escribanos App web con JWT

### Aplicación web que implementa autenticación basada en JSON Web Tokens (JWT) y consume los endpoints de la API del Colegio de Escribanos. 

### Características Principales

- Validación de formulario
- Validación de la petición HTTP
- Manejo de Token
- Consumo de API externa
- Manejo de errores

### Tecnologías

#### Back-end:

- Java 11
- Maven 3.6.3
- Spring boot 2.4.13
- Tomcat 9

#### Fron-tend:

- Thymeleaf
- jQuery
- Bootstrap 4

### Cómo ejecutar
1 - Cambiar a la rama `ejecucion-sin-ide` (https://github.com/ariel05/escribanos-webclient-jwt/tree/ejecucion-sin-ide)

2 - Si ya tenés java 11 en el sistema (java --version)
   - Ejecutar archivo `ejecutar-windows-v2.bat` en windows o `ejecutar-linux-v2.sh` para linux (o git bash)

3 - Si no tenés java 11 en el sistema, colocar la carpeta de jdk 11 en la raíz del proyecto y renombrarlo a `jdk11`
   - Ejecutar archivo `ejecutar-windows.bat` en windows o `ejecutar-linux.sh` para linux (o git bash)

4 - Acceder a `localhost:8080/` o `localhost:8080/nomina`

5 - Realizar consulta rest en `http://localhost:8080/api/v1/token` que devuelven el token a usar en https://servicios-testing.colegio-escribanos.org.ar:8444/nomina-escribanos-ws/swagger-ui.html#/consultas-controller/getByCuitUsingGET
![image](https://github.com/user-attachments/assets/03cec54d-db78-477d-8091-2ff0e28c9cc3)



## Nota de Seguridad:
Tanto el `jwt.secret` definido en el archivo `application.properties` como la obtención del token por rest (`/token` y  `/tokenV2`) son exclusivamente con fines de simplicidad para esta prueba técnica.
En un entorno real, estos valores debería manejarse con:
- Variables de entorno
- Un sistema de gestión de secretos como AWS Secrets Manager, Azure Key Vault o Spring Cloud Vault.
- Obtención del token para usuarios logeados con roles específicos.

## Autor

- **Nombre:** Ariel Rivero
- **Perfil:** [@ariel05](https://github.com/ariel05)
- **Email:** ariel200506@gmail.com
- **Rol:** Desarrollador Backend - Java | Spring Boot | APIs REST
