FROM openjdk:17
LABEL maintainer="Fatih Camgoz"
ENV CONFIG_SERVER_URL=http://localhost:8888
COPY target/api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]