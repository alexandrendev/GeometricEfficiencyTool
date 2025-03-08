FROM openjdk:17
WORKDIR /app
COPY target/GeometricEfficiencyTool-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENV MONGO_URI=""
CMD ["java", "-Dspring.data.mongodb.uri=${MONGO_URI}", "-jar", "app.jar"]

