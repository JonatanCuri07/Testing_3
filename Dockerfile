FROM amazoncorretto:11-alpine-jdk
MAINTAINER GB
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]