# Spring Boot Config Server


## Steps to setup

**4. Build docker image and run**
```bash
./mvnw install dockerfile:build
```

**5. Run application with docker-compose**
```bash
docker-compose up
```
You can get configs from localhost:7777/{config-name}