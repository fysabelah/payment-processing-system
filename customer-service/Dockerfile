#
# Build stage
#
FROM maven:3.9.6-amazoncorretto-21 AS build

WORKDIR /customer

COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY src ./src
RUN mvn -B package -DskipTests

#
# Package stage
#
FROM amazoncorretto:21-alpine-jdk

WORKDIR /customer

COPY --from=build /customer/target/*.jar ./customer-service.jar

EXPOSE 7076

ENTRYPOINT ["java","-jar","customer-service.jar"]