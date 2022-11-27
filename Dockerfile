FROM gradle:jdk11-alpine AS builder
COPY ./EyesTalkDB/gradlew .
COPY ./EyesTalkDB/gradle gradle
COPY ./EyesTalkDB/build.gradle .
COPY ./EyesTalkDB/settings.gradle .
COPY ./EyesTalkDB/src src
RUN chmod +x ./gradlew
RUN ./gradlew bootJAR


FROM openjdk:11-jre-slim

COPY --from=builder build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/spring-app.jar", "-Dspring-boot.run.arguments=--SPRING_DATASOURCE_URL=${SPRING_DATASOURCE_URL}, --SPRING_DATABASE_USERNAME=${SPRING_DATABASE_USERNAME}, --SPRING_DATABASE_PASSWORD=${SPRING_DATABASE_PASSWORD}"]]
