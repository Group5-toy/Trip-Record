# 5️⃣조-Toy Project <2단계>
> **패스트캠퍼스 X 야놀자 : 백엔드 개발 부트 캠프**<br>
> **개발기간 : 2023.10.23 ~ 2023.10.29**

## 👨‍💻팀원 소개
|이름|Github 주소|
|----|-----------|
|서원빈|[서원빈님의 Github](https://github.com/Wonbn)|
|박건우|[박건우님의 Github](https://github.com/Parkgeonmoo)|
|장성수|[장성수님의 Github](https://github.com/tjdtn0219)|
|박준모|[박준모님의 Github](https://github.com/junmo95)|

## 📚Stacks

<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white"> <img src="https://img.shields.io/badge/intellijidea-000000?style=for-the-badge&logo=intellijidea&logoColor=white"> <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"> <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=OpenJDK&logoColor=white"> <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/slack-4A154B?style=for-the-badge&logo=slack&logoColor=white"> <img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white"> <img src="https://img.shields.io/badge/postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white"> <img src="https://img.shields.io/badge/swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=white"> 

## 📢프로젝트 소개

> **여행 여정을 기록하고 관리하는 SNS 서비스**<br>
> **사용자가 여행 기록과 여행에 따른 여정을 기록할 수 있게 하는 서비스**

### 🗃Entity Relationship Diagram
[ERD 바로가기](https://www.erdcloud.com/d/REXyqoMNpWjHYLJwx)
### 📝RESTful API 명세서
|Method|URL|Request Format|Response Format|Description|
|------|---|--------------|---------------|-----------|
|POST|/trips|name: String<br>start_time : LOCALDATE<br>end_time : LOCALDATE<br>domestic : ENUM|{<br>status : Success,<br>code : xx,<br>data : TripCreateResponse<br>}|여행에 대한 이름, 시작 시간, 종료시간, 국내/외 여부를 저장|
|PATCH|/trips/{id}|name: String<br>start_time : LOCALDATE<br>end_time : LOCALDATE<br>domestic : ENUM|{<br>status : Success,<br>code : xx,<br>data : TripPatchResponse<br>}|여행에 대한 이름 또는 시작 시간, 종료시간, 국내/외 여부를 일부 변경한다.|
|PUT|/trips/{id}|name: String<br>start_time : LOCALDATE<br>end_time : LOCALDATE<br>domestic : ENUM|{<br>status : Success,<br>code : xx,<br>data : TripUpdateResponse<br>}|여행에 대한 이름, 시작 시간, 종료시간, 국내/외 여부를 전부 변경한다.|
|GET|/trips/{id}|-|{<br>status : Success,<br>code : xx,<br>data : TripEntryResponse<br>}|단 건의 여행 이름과 그에 속한 여정들에 대한 정보들을 조회해온다.|
|GET|/trips/all|-|{<br>status : Success,<br>code : xx,<br>data : data : List\<TripEntryResponse><br>}|다 건의 여행과 그에 속한 여정 이름들을 조회해온다.|
|POST|/trips/{tripId}/journeys|List\<MoveJourney> <br> List\<StayJourney> <br> List\<LiveJourney>|{<br>status : Success,<br>code : xx,<br>data : SuperJourneyResponse<br>}|특정 여행의 여정목록을 저장한다.|
|PUT|/journeys/{id}|type: String<br>name: String<br>vehicle: String<br>start_point: String<br>end_point: String<br>start_date: LOCALDATE<br>end_date: LOCALDATE<br>domitory_name: String<br>location: String|{<br>status : Success,<br>code : xx,<br>data : SuperJourneyResponse<br>}|특정 여정의 내용을 수정한다.|
