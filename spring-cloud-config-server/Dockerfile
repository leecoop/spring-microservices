FROM maven:3.5.2-jdk-8-alpine AS BUILD
WORKDIR /tmp/

COPY pom.xml /tmp/
COPY src /tmp/src/

RUN mvn install

FROM openjdk:8-jre-alpine
EXPOSE 8888
COPY --from=BUILD /tmp/target/spring-cloud-config-server-0.0.1-SNAPSHOT.jar config-server.jar config-server.jar
ENTRYPOINT ["java","-jar","config-server.jar"]
