# spring-back


## Swagger
![](https://velog.velcdn.com/images/jupiter-j/post/0eb6de91-be94-405f-8272-6b1e5aaad2b0/image.png)
## 기능 명세서
<img width="969" alt="image" src="https://user-images.githubusercontent.com/73453283/203210421-be334875-c0de-4f61-9c7c-55784e682c98.png">

## ERD
![image](https://user-images.githubusercontent.com/80441723/203263365-83bf3ba1-2591-40ae-af8d-4cc55b395836.png)

## 환경변수 설정해서 실행하기

---

```bash
spring.datasource.url=jdbc:mysql://mariadb:3306/eyestalk?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=${DATABASE-USERNAME}
spring.datasource.password=${DATABASE-PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.mvc.pathmatch.matching-strategy=ant_path_matcher

# 콘솔에 SQL 출력 여부
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

# hibernate 설정
spring.jpa.database=mysql
spring.jpa.hibernate.ddl-auto=create
spring.jpa.hibernate.naming.strategy=org.hibernate.cfg.ImprovedNamingStrategy
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
spring.jpa.generate-ddl=false
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true
```

### DB Dockerfile

```jsx
FROM mariadb:10.2

ENV DATABASE-USERNAME root
ENV DATABASE-PASSWORD pass123#
ENV MYSQL_DATABASE eyestalk
CMD ["--character-set-server=utf8", "--collation-server=utf8_general_ci"]
```

### Spring Boot Dockerfile

```yaml
FROM openjdk:11-jdk AS builder

RUN apt-get -y update && apt-get -y install default-jre && \
        apt-get clean -y && \
        apt-get autoremove -y && \
        rm -rfv /tmp/* /var/lib/apt/lists/* /var/tmp/*

ADD Eyes-talk-db-0.0.1-SNAPSHOT.jar spring-app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/spring-app.jar", "-Dspring-boot.run.arguments=--DATABASE-USERNAME=${DATABASE-USERNAME}, --DATABASE-PASSWORD=${DATABASE-PASSWORD}"]]
```

### build & run
```yaml
sudo docker build -t mydb:1.0 . 
sudo docker build -t spring:1.0 . 

sudo docker run -d --name spring-container -p 8080:8080 --link mariadb spring:1.0 --DATABASE-USERNAME=root --DATABASE-PASSWORD=pass123#
sudo docker run --name mariadb -p 3306:3306 -e MARIADB_ROOT_PASSWORD=pass123# -d mydb:1.0
```
