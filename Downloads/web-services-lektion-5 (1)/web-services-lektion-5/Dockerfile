FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/web-services-lektion-5-0.0.1-SNAPSHOT.jar /app/my-spring-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/my-spring-app.jar"]

