# Spring Boot Flyway MySQL Scaffold

## Requirements

1. Java - 1.8.x

2. Maven - 3.x.x

3. MySQL - 5.x.x

## Steps to setup

**1. Setup Mysql database**
```bash
docker run --name store-mysql-for-test -p 3307:3306  -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=store -e MYSQL_USER=mysql -e MYSQL_PASSWORD=mysql -d mysql:5.6
```
**2. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation


<!---
**3. Build and run the app using maven**
```bash
cd scaffold-demo
mvn package
java -jar target/scaffold-0.0.1-SNAPSHOT.jar
```

You can also run the app without packaging it using -

```bash
mvn spring-boot:run
```
--->

**4. Build docker image and run**
```bash
./mvnw install dockerfile:build
docker run -p 8080:8080 --name scaffold-app --link scaffold-mysql:db -d springio/scaffold
```
You can see the website at localhost:8080/users

**5. Run application with docker-compose**
```bash
docker-compose up
```
You can see the website at localhost:8888/users