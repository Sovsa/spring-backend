#
# Build stage
#
FROM maven:3.9.7-eclipse-temurin-21 AS build
WORKDIR /home/backend-app
COPY pom.xml .
RUN mvn verify --fail-never
COPY src ./src
RUN mvn package
EXPOSE 8081
ENTRYPOINT ["java","-jar","/home/backend-app/target/spring-backend-0.0.1-SNAPSHOT.jar"]