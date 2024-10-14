FROM registry.access.redhat.com/ubi8/openjdk-17:latest
LABEL maintainer="Fatih Camgoz"
ENV CONFIG_SERVER_URL=http://localhost:8888
RUN mvn -version
# Set up application directory
WORKDIR /tmp/app
COPY . /tmp/app
# Run the built Spring Boot application
CMD ["java", "-jar", "target/api-0.0.1-SNAPSHOT.jar"]