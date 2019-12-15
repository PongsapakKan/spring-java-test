FROM openjdk:8u151-jre-alpine

WORKDIR /

ADD target/userinfo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]