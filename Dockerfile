FROM gradle:jdk11-alpine AS builder
COPY ./EyesTalkDB .
RUN chmod +x ./EyesTalkDB/gradlew
RUN ./EyesTalkDB/gradlew bootJAR


FROM openjdk:11-jre-slim

COPY --from=builder ./build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/spring-app.jar", "-Dspring-boot.run.arguments=--SPRING_DATASOURCE_URL=${SPRING_DATASOURCE_URL}, --SPRING_DATABASE_USERNAME=${SPRING_DATABASE_USERNAME}, --SPRING_DATABASE_PASSWORD=${SPRING_DATABASE_PASSWORD}"]]
