
FROM adoptopenjdk/openjdk11:alpine


WORKDIR /app

COPY target/Purchase-0.0.1-SNAPSHOT.jar /app/Purchase-0.0.1-SNAPSHOT.jar


CMD ["java", "-jar", "/app/Purchase-0.0.1-SNAPSHOT.jar"]
