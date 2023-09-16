FROM eclipse-temurin:17-jdk-alpine

ARG JAR_FILE=target/authorization-Role.jar

WORKDIR /opt/app

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","authorization-Role..jar"]

## docker build -t myorg/myapp .

##docker run -p 8080:8080 myorg/myapp