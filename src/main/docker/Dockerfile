FROM openjdk:8u181-jre-alpine3.8
VOLUME /tmp
ADD audio-model-0.0.3-SNAPSHOT.jar audio-model-0.0.3-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","audio-model-0.0.3-SNAPSHOT.jar"]
