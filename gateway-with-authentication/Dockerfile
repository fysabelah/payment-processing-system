#
# Build stage
#
FROM maven:3.9.6-amazoncorretto-21 AS build

WORKDIR /gateway

COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY src ./src
RUN mvn -B package -DskipTests

#
# Package stage
#
FROM amazoncorretto:21-alpine-jdk

WORKDIR /gateway

COPY --from=build /gateway/target/*.jar ./gateway.jar

EXPOSE 7071

ENTRYPOINT ["java","-jar","gateway.jar"]