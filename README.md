# 5️⃣조-Toy Project <2단계>
> **패스트캠퍼스 X 야놀자 : 백엔드 개발 부트 캠프**<br>
> **개발기간 : 2023.10.23 ~ 2023.10.29**

## 👨‍💻 팀원 소개
|이름|Github 주소|
|----|-----------|
|서원빈|[서원빈님의 Github](https://github.com/Wonbn)|
|박건우|[박건우님의 Github](https://github.com/Parkgeonmoo)|
|장성수|[장성수님의 Github](https://github.com/tjdtn0219)|
|박준모|[박준모님의 Github](https://github.com/junmo95)|

## 📚 Stacks

<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white"> <img src="https://img.shields.io/badge/intellijidea-000000?style=for-the-badge&logo=intellijidea&logoColor=white"> <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"> <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=OpenJDK&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/slack-4A154B?style=for-the-badge&logo=slack&logoColor=white"> <img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white"> <img src="https://img.shields.io/badge/postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white"> <img src="https://img.shields.io/badge/swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=white"> 

## 📢 프로젝트 소개

> **여행 여정을 기록하고 관리하는 SNS 서비스**<br>
> **사용자가 여행 기록과 여행에 따른 여정을 기록할 수 있게 하는 서비스**

### 🗃 Entity Relationship Diagram
[ERD 바로가기](https://www.erdcloud.com/d/fzkBGiKt6BMvxWDog)

### 📝 RESTful API 명세서
|Method|URL|Request Format|Response Format|Description|
|------|---|--------------|---------------|-----------|
|POST|/trips|name: String<br>start_time : LOCALDATE<br>end_time : LOCALDATE<br>domestic : ENUM|{<br>status : Success,<br>code : xx,<br>data : TripCreateResponse<br>}|여행에 대한 이름, 시작 시간, 종료시간, 국내/외 여부를 저장|
|PATCH|/trips/{id}|name: String<br>start_time : LOCALDATE<br>end_time : LOCALDATE<br>domestic : ENUM|{<br>status : Success,<br>code : xx,<br>data : TripPatchResponse<br>}|여행에 대한 이름 또는 시작 시간, 종료시간, 국내/외 여부를 일부 변경한다.|
|PUT|/trips/{id}|name: String<br>start_time : LOCALDATE<br>end_time : LOCALDATE<br>domestic : ENUM|{<br>status : Success,<br>code : xx,<br>data : TripUpdateResponse<br>}|여행에 대한 이름, 시작 시간, 종료시간, 국내/외 여부를 전부 변경한다.|
|GET|/trips/{id}|-|{<br>status : Success,<br>code : xx,<br>data : TripEntryResponse<br>}|단 건의 여행 이름과 그에 속한 여정들에 대한 정보들을 조회해온다.|
|GET|/trips/all|-|{<br>status : Success,<br>code : xx,<br>data : data : List\<TripEntryResponse><br>}|다 건의 여행과 그에 속한 여정 이름들을 조회해온다.|
|POST|/trips/{tripId}/journeys|List\<MoveJourney> <br> List\<StayJourney> <br> List\<LiveJourney>|{<br>status : Success,<br>code : xx,<br>data : SuperJourneyResponse<br>}|특정 여행의 여정목록을 저장한다.|
|PUT|/journeys/move/{id}|name: String<br>vehicle: String<br>start_point: String<br>end_point: String<br>start_time: LOCALDATE<br>end_time: LOCALDATE|{<br>status : Success,<br>code : xx,<br>data : moveJourneyUpdateResponse<br>}|이동여정의 내용을 수정한다.|
|PUT|/journeys/visit/{id}|name: String<br>location: String<br>start_time: LOCALDATE<br>end_time: LOCALDATE|{<br>status : Success,<br>code : xx,<br>data : visitJourneyUpdateResponse<br>}|방문여정의 내용을 수정한다.|
|PUT|/journeys/lodgment/{id}|name: String<br>domitory_name: String<br>start_time: LOCALDATE<br>end_time: LOCALDATE|{<br>status : Success,<br>code : xx,<br>data : lodgmentJourneyUpdateResponse<br>}|숙박여정의 내용을 수정한다.|


## 📦 Docker 실행방법 (MySQL Server Container)
### 개요

협업 개발을 하는데 있어 SpringBoot 프로젝트까지는 버전을 공유하는 것이 쉽게 가능하나, MySQL과 같은 외부 애플리케이션의 버전을 맞추고 후에 배포까지 고려하는 것은 매우 힘들다고 판단했습니다.

그래서 모두 같은 환경을 공유할 수 있는 MySQL 도커 컨테이너를 만들어 운영하기로 결정했습니다.

<img width="179" alt="스크린샷 2023-10-28 151421" src="https://github.com/Group5-toy/KDT_Y_BE_Toy_Project2/assets/97028441/9000bc13-1ba4-4721-8a3d-c96ad11004d9">

그래서 위의 그림과 같은 관계를 가지고 동작합니다.

1. SpringBoot 에서 3305Port로 DB관련 요청을 보냅니다.
2. Docker Engine이  3305Port 요청을 받습니다.
3. 설정에 딸라 Docker Engine이 3305Port 요청을 MySQL의 포트인 3306Port로 전달해줌으로서 동작하게 됩니다.


### 동작법

#### Docker 설치

도커를 실행시키기 위해서는 실행환경에 Docker가 설치되어있어야합니다.

Window의 경우 **WSL**이라는 Windows 운영체제에서 경량화된 가상화 기술을 사용하여 Linux 운영 체제를 구동할 수 있도록 해 주는 프로그램을 까신 후, DockerDesktop을 설치하면 됩니다.

아래 잘 정리된 설치 관련 블로그 글입니다.

WSL 설치

[WSL2 사용 설정(윈도우에서 Ubuntu 사용하는 방법)](https://axce.tistory.com/110?category=1030982)

Docker Desktop 설치

[[Docker] 윈도우 도커 설치방법(window 11)](https://axce.tistory.com/121)


#### 실행

Docker 설치를 마치셨으면 이제 Docker Conmtainer을 실행시킬 수 있습니다.

![스크린샷 2023-10-28 151421.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/090b1c92-03e6-4ae9-8e00-eb34672bf01c/4071fdb9-0f58-487b-93a3-7797b8e7b2f7/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7_2023-10-28_151421.png)

docker-compose.yml 파일에서 빨간상자로 표시된 실행버튼을 클릭하시면 자동으로 MySQL 컨테이너를 생성하여 동작시킵니다.

그렇게 되면 3305 포트로 연결되는 MySQL 서버가 동작하여 SpringBoot와 연결되어 동작하게 됩니다.

#### 추가정리페이지
https://www.notion.so/geonmoo/Docker-71a7fdefc73042e9b91e525a08adb364


## 💡 실행결과 (Postman)
### 1. 여행 전체 조회
* **요청**
<br>![GET all Trip Request](https://github.com/Group5-toy/KDT_Y_BE_Toy_Project2/assets/81563920/2273f0c4-83dc-4a3c-83bb-64409a3aa9ff)
* **응답**
<br>![GET all Trip Response 01 png](https://github.com/Group5-toy/KDT_Y_BE_Toy_Project2/assets/81563920/9a7abdde-1f68-4f76-955e-2567f4c4270c)
<br>![GET all Trip Response 02 png png](https://github.com/Group5-toy/KDT_Y_BE_Toy_Project2/assets/81563920/121ab98b-1042-4191-8947-39189b5f1783)

### 2. 여행 단건 조회
* **요청**
<br>![GET Trip Request](https://github.com/Group5-toy/KDT_Y_BE_Toy_Project2/assets/81563920/6f18da69-98c8-43e5-a470-06042f6182d3)
* **응답**
<br>![GET Trip Response](https://github.com/Group5-toy/KDT_Y_BE_Toy_Project2/assets/81563920/0b712cc0-07cc-46a0-a095-2995922463f2)

### 3. 여행 등록
* **요청**
<br>![POST Trip Request](https://github.com/Group5-toy/KDT_Y_BE_Toy_Project2/assets/81563920/34cf1c24-85c4-49fb-9df8-a0c858569e10)
* **응답**
<br>![POST Trip Response](https://github.com/Group5-toy/KDT_Y_BE_Toy_Project2/assets/81563920/1431a9e4-117b-46a8-a6da-f7f8d192c409)

### 4. 여행 정보 일부 수정
* **요청**
<br>![PATCH Trip Request](https://github.com/Group5-toy/KDT_Y_BE_Toy_Project2/assets/81563920/1add29f8-5f9d-41b6-a47c-b5ab82a6cd62)
* **응답**
<br>![PATCH Trip Response](https://github.com/Group5-toy/KDT_Y_BE_Toy_Project2/assets/81563920/cec007d0-b4f9-4ef4-92bc-aa4bc3b2e412)

### 5. 여행 정보 전체 수정
* **요청**
<br>![PUT Trip Request](https://github.com/Group5-toy/KDT_Y_BE_Toy_Project2/assets/81563920/8955707c-bcf3-43b1-b296-fc60729beea5)
* **응답**
<br>![PUT Trip Response](https://github.com/Group5-toy/KDT_Y_BE_Toy_Project2/assets/81563920/eb93a4ad-55a1-479d-9eda-866fd0b167af)

### 6. 여정 등록
* **요청**
<br>![POST Journey Request](https://github.com/Group5-toy/KDT_Y_BE_Toy_Project2/assets/81563920/6198243d-ad5a-4bbc-9894-4a758f9a476f)
* **응답**
<br>![POST Journey Response](https://github.com/Group5-toy/KDT_Y_BE_Toy_Project2/assets/81563920/f990c558-a9fa-4a34-b8c9-52acf4e910b1)

### 7. 여정 정보 수정
### 7-1. 이동정보
* **요청**
<br>![PUT MoveJourney Request](https://github.com/Group5-toy/KDT_Y_BE_Toy_Project2/assets/81563920/76535a9d-7654-42f1-aa11-12b1a6c794eb)
* **응답**
<br>![PUT MoveJourney Response](https://github.com/Group5-toy/KDT_Y_BE_Toy_Project2/assets/81563920/1f075665-ed1e-4ce2-911c-c48dc101a891)
### 7-2. 숙박정보
* **요청**
<br>![PUT LodgmentJourney Request](https://github.com/Group5-toy/KDT_Y_BE_Toy_Project2/assets/81563920/32866547-6e13-4af0-8724-0851d359a712)
* **응답**
<br>![PUT LodgmentJourney Response](https://github.com/Group5-toy/KDT_Y_BE_Toy_Project2/assets/81563920/85037782-482c-4ebc-93f9-7937565b5324)
### 7-3. 방문정보
* **요청**
<br>![PUT VisitJourney Request](https://github.com/Group5-toy/KDT_Y_BE_Toy_Project2/assets/81563920/a8e441fc-56ca-40a8-a086-b3bac63077c2)
* **응답**
<br>![PUT VisitJourney Response](https://github.com/Group5-toy/KDT_Y_BE_Toy_Project2/assets/81563920/679b3eca-64db-4e9b-9c70-78a124380e0f)
