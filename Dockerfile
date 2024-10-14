FROM registry.access.redhat.com/ubi8/openjdk-17:latest
LABEL maintainer="Fatih Camgoz"
ENV CONFIG_SERVER_URL=http://localhost:8888
RUN yum install -y maven && \
    mvn -version
COPY . /app
WORKDIR /app
RUN mvn clean package