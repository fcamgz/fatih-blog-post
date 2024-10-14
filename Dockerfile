FROM registry.access.redhat.com/ubi8/openjdk-17:latest
LABEL maintainer="Fatih Camgoz"
ENV CONFIG_SERVER_URL=http://localhost:8888
# Install Maven using dnf
RUN dnf install -y maven && \
    mvn -version
# Continue with your application setup
COPY . /app
WORKDIR /app
RUN mvn clean package