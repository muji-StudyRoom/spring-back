# spring-back


## ☀️ Swagger
![image](https://user-images.githubusercontent.com/73453283/205673469-3d767d6f-c808-46de-9ee1-39ed7a3b5dc5.png)
<br>

## ☀️ 기능 명세서
| 방 생성 | 사용자는 방 제목(roomName), 수용인원(roomCapactiy)을 입력하여 회의실를 생성할 수 있다. 이때 사용자의 데이터도 작성해야 한다 닉네임(userNickName) |
| --- | --- |
|  | room 테이블에 저장되는 데이터 {roomId, roomName(100), roomCapacity, roomPassword(100), roomCreateAt, roomEnterUser}user 테이블에 저장되는 데이터 {userId, userNickName, userCreateAt, roomEntity} |
|  | 1. 동일한 방 이름이 있는지 체크한다. 중복이 있을경우, DUPLICATE_RESOURCE 에러코드 반환 |
|  | 2. 동일한 닉네임으로 생성된 룸이 있는지 체크한다, 중복이 있을 경우 INVALID_NICKNAME 에러코드 반환 |
|  | 3. 위의 모든 조건이 충족되면 유저정보를 저장한다이때 방 정보에서 현재 방에 입장한 인원(roomEnterUser)값을 1 증가시킨다변경된 방 정보를 저장후 저장된 데이터들을 반환한다  |
| 방 검색 | 사용자는 방 이름(roomName)으로 방을 검색할 수 있다. |
|  | 1. 방 이름이 없는 경우 ROOM_IS_EMPTY에러코드 반환 |
|  | 2. 방 이름을 찾으면 데이터 값을 반환한다  |
| 방 입장 | 사용자는 닉네임(userNickName), 비밀번호(userPassword)를 입력하여 방에 입장할 수 있다. 이때 PatchVariable로 roomId값이 필요하다 |
|  | 1. 방 아이디가 있는지 체크한다. 없을경우, ROOM_IS_EMPTY 에러코드 반환 |
|  | 2. 방 비밀번호가 맞는지 체크한다. 맞지 않을경우, INVALID_PASSWORD 에러코드 반환 |
|  | 3. 동일한 닉네임으로 생성된 룸이 있는지 체크한다, 중복이 있을 경우 INVALID_NICKNAME 에러코드 반환 |
|  | 4. 수용인원과 현재 입장한 인원수를 비교한다. 수용인원이 찼을경우 FULL_CAPACITY 에러코드 반환 |
|  | 5. 위의 모든 조건이 만족되면 유저를 생성하고 저장한 유저의 값과 방의 데이터를 반환한다 |
| 사용자 나가기 & 방 삭제 | 현재 방에 입장한 인원(roomEnterUser)이 1일때 사용자 나가기 요청이 오면 방은 사용자와 함께 자동으로 삭제된다 입장한 인원이 2명이상 남아 있을 때 사용자만 나가게된다 |
|  | 1. 방 아이디(roomId)가 없을경우 ROOM_IS_EMPTY 에러코드 반환 |
|  | 2. 유저 아이디 (userId)가 없을경우 MEMBER_NOT_FOUND 에러코드 반환 |
|  | 3. 현재 방에 입장한 인원(roomEnterUser)이 1보다 클때 방의 입장한 인원을 1명 줄이고 update된 방 정보를 저장 후 유저의 값을 삭제한다 입장인원이 1보다 작을경우, 유저와 방을 모두 삭제한다  |
| 방 생성 검증  | 사용자가 확인버튼을 누르면 방을 생성할 수 있는지 검증한다.  |
|  | 1. 방이름이 중복되는 경우에는 false 를 반환 |
|  | 2. 방이름이 중복되지 않는다면 True 를 반환 |
| 방 입장 검증 | 사용자가 확인버튼을 누르면 방을 입장할 수 있는지 검증한다. |
|  | 1. 같은방에 이미 같은 닉네임의 사용자가 있다면 nickname_error 를 반환한다.  |
|  | 2. 방의 정원이 이미 가득차있는 상태라면 capacity_error 를 반환한다. |
|  | 3. 방의 비밀번호를 틀렸다면 password_error 를 반환한다.  |
|  | 4. 1~3 의 조건을 통과했다면 200 반환  |
| 전체 방 조회 | 전체방 리스트를 조회한다.  |

<br>

## ☀️ ERD
![image](https://user-images.githubusercontent.com/80441723/203263365-83bf3ba1-2591-40ae-af8d-4cc55b395836.png)

<br>

## ☀️ 도커 실행 설정 

---

### Properties
```jsx
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
