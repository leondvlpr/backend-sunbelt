FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/pruebatecnica.jar .
CMD ["java", "-jar", "your-application.jar"]