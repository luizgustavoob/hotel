FROM openjdk:8 AS builder
RUN apt-get update && apt-get install -y maven
COPY pom.xml /app/pom.xml
COPY src /app/src
WORKDIR /app
RUN mvn clean package
    
FROM openjdk:8
WORKDIR /deployed-app
COPY --from=builder /app/target/hotel-api.jar .
ENTRYPOINT ["java", "-jar", "hotel-api.jar"]

EXPOSE 8086