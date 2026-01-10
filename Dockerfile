FROM eclipse-temurin:24-jdk

WORKDIR /app

COPY target/ValMatchPredictor-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

CMD [ "java", "-jar", "app.jar" ]