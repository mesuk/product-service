FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} user-service.jar
EXPOSE 9090-9099

ENTRYPOINT ["java","-jar","/user-service.jar"]