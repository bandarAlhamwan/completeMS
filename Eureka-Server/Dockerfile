FROM eclipse-temurin:17-jdk-alpine

ARG JAR_FILE=target/Eureka-Server.jar

WORKDIR /opt/app

COPY ${JAR_FILE} Eureka-Server.jar

ENTRYPOINT ["java","-jar","Eureka-Server.jar"]

## docker build -t eureka/server .

##docker run -p 8761:8761 eureka/server