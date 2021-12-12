FROM openjdk:14-alpine

WORKDIR /opt/server
COPY ./target/service-users-0.0.1-SNAPSHOT.jar server.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "server.jar"]
