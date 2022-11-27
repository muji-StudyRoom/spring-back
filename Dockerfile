FROM gradle:7.5.1-jdk11-alpine AS builder
COPY ./EyesTalkDB ./EyesTalkDB
WORKDIR ./EyesTalkDB
RUN chmod +x ./gradlew
RUN ./gradlew bootJar


FROM openjdk:11-jre-slim

COPY --from=builder ./build/libs/Eyes-talk-db-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "./app.jar", "-Dspring-boot.run.arguments=--SPRING_DATASOURCE_URL=${SPRING_DATASOURCE_URL}, --SPRING_DATABASE_USERNAME=${SPRING_DATABASE_USERNAME}, --SPRING_DATABASE_PASSWORD=${SPRING_DATABASE_PASSWORD}"]]
