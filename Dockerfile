FROM eclipse-temurin:21
COPY target/Golf-Club-Tournament-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
