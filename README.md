# Clinica Dental App 🏣🦷

*Welcome to the dental clinic app, an application designed to perform CRUD requests of dentists, patients and shifts of a dental clinic.*

### About the project 🚀
This project was developed using JAVA, Spring and Hibernate to build a REST API to perform the complete CRUD of a dental clinic. H2 Database and Spring Data JPA were used to manage the database, finally the connection to the API with the FrontEnd was done using Javascript.
### 🚨 Requirements

This project was created with Spring using the following tools:

- [Spring Initializr](https://start.spring.io/)

- [Java 17](https://www.oracle.com/co/java/technologies/downloads/#java17)

- [Maven](https://maven.apache.org/download.cgi)

- [IntelliJ IDEA](https://www.jetbrains.com/idea/)

#### *Dependencies*

- Spring Web
- H2 Database
- Spring Data JPA
- Lombok
----
### 🌟 Implementation

#####  Via IntelliJ IDEA

1. Clone this repository
```
$ git clone https://github.com/XimenaLargo96/ClinicaDentalApp
```
2. Open the ClinicaDentalApp folder with IntelliJ IDEA
3. Once opened you should see the following structure
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
4. Next we will go to src > main > java > com.dh.ClinicaDental and run the ClinicaDentalApplication

5. Once the program is run, the folder structure will change and the target folder will be added, as shown below
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
7. Finally, to view the application from the browser, go to http://localhost:8080/. 

#####  From Terminal

1. Clone this repository
```
$ git clone https://github.com/XimenaLargo96/ClinicaDentalApp
```
2. Move to the project folder
```
$ cd ClinicaDentalApp
```
3. This is the structure of the project
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
4. IMPORTANT !!! You must have installed Maven, to see the guide click [here](https://maven.apache.org/install.html)
5. Once you make sure you have Maven run the following command: 
```
$ mvn package
```
6. The project structure will change and the target folder will be added.
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
7. To execute the project we use the following command: 
```
$ java -jar target/final-project-0.0.1-SNAPSHOT.jar
```
8. Finally, to view the application from the browser, go to http://localhost:8080/.

Our app should look like this 
<img src="https://github.com/XimenaLargo96/XimenaLargo96/blob/main/publicImages/clinica-dental.gif?raw=true" />
