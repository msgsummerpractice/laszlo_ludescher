FROM eclipse-temurin:25-jdk-alpine
 
WORKDIR /src/
 
COPY target/*.jar app.jar
 
EXPOSE 8081
 
ENTRYPOINT ["java","-jar","app.jar"]