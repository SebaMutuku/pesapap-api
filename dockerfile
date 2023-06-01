# Use an appropriate base image for your Maven project
FROM maven:3.8.4-openjdk-11-slim AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
# Build the Maven project
RUN mvn package -DskipTests
FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /app/target/Pesapap-api-V1-1.0-V1-SNAPSHOT.jar Pesapapi-v1.jar
EXPOSE 9096
CMD ["java", "-jar", "app.jar"]
