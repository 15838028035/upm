FROM adoptopenjdk/openjdk8-openj9:alpine-slim
VOLUME /tmp
RUN mkdir app
RUN mkdir -p /app/config

ADD upm-0.0.1-SNAPSHOT.jar /app/upm-0.0.1-SNAPSHOT.jar
COPY config/* /app/config/

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/upm-0.0.1-SNAPSHOT.jar"]
