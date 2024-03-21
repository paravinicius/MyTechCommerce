FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM amazoncorretto:17
WORKDIR /application
COPY --from=builder /app/target/*.jar application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Xmx2048M", "-jar", "application.jar"]
