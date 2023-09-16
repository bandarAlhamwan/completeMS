FROM eclipse-temurin:17-jdk-alpine

ARG JAR_FILE=target/admin-server.jar

WORKDIR /opt/app

COPY ${JAR_FILE} admin-server.jar

ENTRYPOINT ["java","-jar","admin-server.jar"]

## docker build -t admin/server .

## docker run -p 8765:8765 admin/server