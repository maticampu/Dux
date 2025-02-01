Maven version: 3.6.3
Jdk: amazoncorretto:17.0.14-alpine

Abrir una terminal en la carpeta principal del archivo
Ejecutar el comando mvn clean install para que se genere el archivo "demo-0.0.1-SNAPSHOT.jar".
Ejecutar el comando docker build -t dux .
Ejecutar el comando docker run -p 8080:8080 dux.Una vez terminado la aplicacion deberia estar corriendo a traves del puerto 8080.

Swagger: http://localhost:8080/doc/swagger-ui/swagger-ui/index.html

H2:http://localhost:8080/h2-console
Driver class: org.h2.Driver
JDBC URL: jdbc:h2:~/test
Username: sa
Password: password

Para la autenticación, se debe poder enviar una petición a /auth/login con body { "username": "test", "password": "12345" }, la cual devolvera un token para utilizar en el resto de endpoints con 24hs de validez

