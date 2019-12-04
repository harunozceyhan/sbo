FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
ARG JAR_FILE
COPY src/main/resources/application-dev.yml override.yml
COPY target/sbo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.config.location=/override.yml", "-jar","/app.jar"]