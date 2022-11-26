FROM openjdk:11-jdk AS builder

RUN apt-get -y update && apt-get -y install default-jre && \
        apt-get clean -y && \
        apt-get autoremove -y && \
        rm -rfv /tmp/* /var/lib/apt/lists/* /var/tmp/*


ADD Eyes-talk-db-0.0.1-SNAPSHOT.jar spring-app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/spring-app.jar", "-Dspring-boot.run.arguments=--SPRING_DATASOURCE_URL=${SPRING_DATASOURCE_URL}, --SPRING_DATABASE_USERNAME=${SPRING_DATABASE_USERNAME}, --SPRING_DATABASE_PASSWORD=${SPRING_DATABASE_PASSWORD}"]]
