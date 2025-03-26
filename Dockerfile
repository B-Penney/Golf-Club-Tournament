FROM eclipse-temurin:21
ARG JAR_FILE=target/*.jar
ENTRYPOINT ["java","-jar","/app.jar"]