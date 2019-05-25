FROM openjdk:8u181-jre-alpine3.8
VOLUME /tmp
ADD upm-0.0.1-SNAPSHOT.jar upm-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","upm-0.0.1-SNAPSHOT.jar"]
