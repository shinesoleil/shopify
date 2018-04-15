# Spring Boot Flyway MySQL Scaffold

## Requirements

1. Java - 1.8.x

2. Maven - 3.x.x

3. MySQL - 5.x.x

## Steps to setup

**4. Build docker image and run**
```bash
./mvnw install dockerfile:build
```
You can see the website at localhost:8080/users

**5. Run application with docker-compose**
```bash
docker-compose up
```
You can see the website at localhost:8888/users