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

<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white"> <img src="https://img.shields.io/badge/intellijidea-000000?style=for-the-badge&logo=intellijidea&logoColor=white"> <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"> <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=OpenJDK&logoColor=white"> <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/slack-4A154B?style=for-the-badge&logo=slack&logoColor=white"> <img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white"> <img src="https://img.shields.io/badge/postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white"> <img src="https://img.shields.io/badge/swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=white"> 

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
