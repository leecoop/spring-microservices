FROM openjdk:8-jre-alpine
EXPOSE 8888
COPY target/spring-cloud-config-server-0.0.1-SNAPSHOT.jar config-server.jar
ENTRYPOINT ["java","-jar","config-server.jar"]
