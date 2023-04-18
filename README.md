# Clinica Dental App 🏣🦷

*Bienvenidos a la clinica dental app, una aplicación diseñada realizar peticiones CRUD de los odontologos, pacientes y turnos de una clinica dental.*

### Acerca del proyecto 🚀
Este proyecto se desarrollo utilizando JAVA, Spring e Hibernate para la construción de una API REST que permitiera realizar el CRUD completo de una clinica dental. Como base de datos se utilizó H2 Database y Spring Data JPA para el manejo de la misma, finalmente la parte de la conexión a la API con el FrontEnd se realizó utilizando Javascript.
### 🚨 Requerimientos

Este proyecto fue creado con Spring utilizando las siguientes herramientas:
- [Spring Initializr](https://start.spring.io/)

- [Java 17](https://www.oracle.com/co/java/technologies/downloads/#java17)

- [Maven](https://maven.apache.org/download.cgi)

- [IntelliJ IDEA](https://www.jetbrains.com/idea/)

#### *Dependencias*

- Spring Web
- H2 Database
- Spring Data JPA
- Lombok
----
### 🌟 Ejecución

#####   A través de IntelliJ IDEA

1. Clona este repositorio
```
$ git clone https://github.com/XimenaLargo96/ClinicaDentalApp
```
2. Abre la carpeta ClinicaDentalApp con IntelliJ IDEA
3. Una vez abierta deberás ver la siguiente estructura
```
   ClinicaDentalApp
   │   .mvn
   │   .gitignore
   │   avisos.log
   │   mvnw
   │   mvnw.cmd
   │   pom.xml
   └───src
```
4. A continuacion iremos a src > main > java > com.dh.ClinicaDental y correremos el ClinicaDentalApplication

5. Una vez se ejecute el programa la estructura de las capetas cambiará y se añadirá la carpeta target, como se observa a continuación
```
   ClinicaDentalApp
   │   .mvn
   │   .gitignore
   │   avisos.log
   │   mvnw
   │   mvnw.cmd
   │   pom.xml
   └───src
   └───target
```
7. Finalmente para ver la aplicación desde el navegador ingresaremos a http://localhost:8080/ 

#####  Desde la terminal 

1. Clona este repositorio
```
$ git clone https://github.com/XimenaLargo96/ClinicaDentalApp
```
2. Muevete a la carpeta del proyecto
```
$ cd ClinicaDentalApp
```
3. Está es la estructura del proyecto
```
   ClinicaDentalApp
   │   .mvn
   │   .gitignore
   │   avisos.log
   │   mvnw
   │   mvnw.cmd
   │   pom.xml
   └───src
```
4. ¡¡¡ IMPORTANTE !!! Debes tener instalado Maven, para ver la guía  haz click [Aquí](https://maven.apache.org/install.html)
5. Una vez te asegures de tener Maven ejecuta el siguiente comando: 
```
$ mvn package
```
6. La estrutura del proyecto cambiara y se añadirá la carpeta target
```
   ClinicaDentalApp
   │   .mvn
   │   .gitignore
   │   avisos.log
   │   mvnw
   │   mvnw.cmd
   │   pom.xml
   └───src
   └───target
```
7. Para ejecutar el proyecto utilizamos el siguiente comando: 
```
$ java -jar target/final-project-0.0.1-SNAPSHOT.jar
```
8. Finalmente para ver la aplicación desde el navegador ingresaremos a http://localhost:8080/

Nuestra app deberá verse de esta forma 
<img src="https://github.com/XimenaLargo96/ClinicaDentalApp/blob/master/src/main/resources/static/img/Demo.jpg" />