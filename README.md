# escribanos-webclient-jwt
Aplicación web con autenticación JWT que consume la API del Colegio de Escribanos



 Nota de Seguridad:
El `jwt.secret` está definido en el archivo `application.properties` exclusivamente con fines de simplicidad para esta prueba técnica.
En un entorno real, este valor debería manejarse con:
- Variables de entorno
- Un sistema de gestión de secretos como AWS Secrets Manager, Azure Key Vault o Spring Cloud Vault.