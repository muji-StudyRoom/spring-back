FROM gradle:7.5.1-jdk11-alpine AS builder
RUN pwd
COPY ./EyesTalkDB ./EyesTalkDB
WORKDIR ./EyesTalkDB
RUN pwd
RUN chmod +x ./gradlew
RUN ./gradlew bootJar

FROM openjdk:11-jre-slim
COPY --from=builder /home/gradle/EyesTalkDB/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "./app.jar", "-Dspring-boot.run.arguments=—SPRING_DATASOURCE_URL=${SPRING_DATASOURCE_URL}, —SPRING_DATABASE_USERNAME=${SPRING_DATABASE_USERNAME}, —SPRING_DATABASE_PASSWORD=${SPRING_DATABASE_PASSWORD}"]]
