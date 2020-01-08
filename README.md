# Simple Spring CRUD Application.

## Technologies Used:
* Java
* Spring
* H2 Database

## Hot To Run Application:

On the terminal go to project root and type: **mvn clean install & mvn spring-boot:run**

## Application

Application available on URL: localhost:8080

Available routes:
* GET : localhost:8080/api/vehicles

Get all Vehicles. Can provide query params: make, model, year.
EX: localhost:8080/api/vehicles?make="Toyota"&model="Corolla"&year=2001

* POST : localhost:8080/api/vehicles

Save Vehicle
* GET : localhost:8080/api/vehicles/{id}

Get Vehicle by Id
* PUT : localhost:8080/api/vehicles.

Update Vehicle.
* DELETE : localhost:8080/api/vehicles/{id} 

Delete Vehicle by id.

## H2 Console

H2 database console available on URL: localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:mitchell

User Name: mitchell

Password: mitchell

