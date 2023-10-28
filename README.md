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
|GET|/journeys?tripId={tripId}|-|{<br>status : Success,<br>code : xx,<br>data : data : List\<TripEntryResponse><br>}|tripId에 해당하는 다건의 여정에 대한 정보들을 조회해온다.|
|POST|/trips/{tripId}/journeys|List\<MoveJourney> <br> List\<StayJourney> <br> List\<LiveJourney>|{<br>status : Success,<br>code : xx,<br>data : SuperJourneyResponse<br>}|특정 여행의 여정목록을 저장한다.|
|PUT|/journeys/move/{id}|name: String<br>vehicle: String<br>start_point: String<br>end_point: String<br>start_time: LOCALDATE<br>end_time: LOCALDATE|{<br>status : Success,<br>code : xx,<br>data : moveJourneyUpdateResponse<br>}|이동여정의 내용을 수정한다.|
|PUT|/journeys/visit/{id}|name: String<br>location: String<br>start_time: LOCALDATE<br>end_time: LOCALDATE|{<br>status : Success,<br>code : xx,<br>data : visitJourneyUpdateResponse<br>}|방문여정의 내용을 수정한다.|
|PUT|/journeys/lodgment/{id}|name: String<br>domitory_name: String<br>start_time: LOCALDATE<br>end_time: LOCALDATE|{<br>status : Success,<br>code : xx,<br>data : lodgmentJourneyUpdateResponse<br>}|숙박여정의 내용을 수정한다.|


## 📦 Docker 실행방법 (MySQL Server Container)
### 개요

협업 개발을 하는데 있어 SpringBoot 프로젝트까지는 버전을 공유하는 것이 쉽게 가능하나, MySQL과 같은 외부 애플리케이션의 버전을 맞추고 후에 배포까지 고려하는 것은 매우 힘들다고 판단했습니다.

그래서 모두 같은 환경을 공유할 수 있는 MySQL 도커 컨테이너를 만들어 운영하기로 결정했습니다.

<br>![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F80jU5%2FbtszlYmANBH%2FHlxvlW1kZxYr2DcEnf6MKk%2Fimg.png)

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


<br>![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F80jU5%2FbtszlYmANBH%2FHlxvlW1kZxYr2DcEnf6MKk%2Fimg.png)


docker-compose.yml 파일에서 빨간상자로 표시된 실행버튼을 클릭하시면 자동으로 MySQL 컨테이너를 생성하여 동작시킵니다.

그렇게 되면 3305 포트로 연결되는 MySQL 서버가 동작하여 SpringBoot와 연결되어 동작하게 됩니다.

#### 추가정리페이지
https://www.notion.so/geonmoo/Docker-71a7fdefc73042e9b91e525a08adb364


## 💡 실행결과 (Postman)
### 1. 여행 전체 조회
* **요청**
<br>![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fm8Ski%2FbtszjugafGi%2FCvYNEkTauXRmkDbTFSESO1%2Fimg.png)
* **응답**
<br>![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbNjOP1%2FbtszjzuRy0K%2FIBuqwyZgOx5sK3RsZHPee0%2Fimg.png)
<br>![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FTEAHH%2FbtsziQju6Tp%2F5n5k1LgVuELhdVuaItKN7k%2Fimg.png)

### 2. 여행 단건 조회
### 2-1. Pathvariable
* **요청**
<br>![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FdcFJfc%2FbtsznAltvWa%2FTkIpJG4DY1MBWu8QuJCyPK%2Fimg.png)
* **응답**
<br>![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbLoov1%2FbtszlvEW8dZ%2FPbuV7HFtvldB9xsnfsSvlk%2Fimg.png)
### 2-2. QueryParam
* **요청**
<br>![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FRdwUg%2FbtszmAzalCB%2Fpj4cZShknL3eeajeoYnde0%2Fimg.png)
* **응답**
<br>![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FlzCJt%2Fbtszlj5KKft%2F5KgvBjhDefeKw3XRWKyjA0%2Fimg.png)


### 3. 여행 등록
* **요청**
<br>![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbVswVr%2FbtszjFV8u5n%2F6lZI1L2cRUXSn42IwEKTNk%2Fimg.png)
* **응답**
<br>![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbWt52p%2Fbtszj33IwMb%2FPyFB4OOY5kp8vko7VtbDN1%2Fimg.png)

### 4. 여행 정보 일부 수정
* **요청**
<br>![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbl21JV%2FbtszhunlRnB%2FGSE27lixfOQ9Sji8kLJKM0%2Fimg.png)
* **응답**
<br>![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F09Mc2%2FbtszlHZBRDo%2FHkGplxldXpzPJYPYyuug7K%2Fimg.png)

### 5. 여행 정보 전체 수정
* **요청**
<br>![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FdlQYLd%2Fbtszk3aSEba%2FmE9u3IPc2exUzoalINBeR0%2Fimg.png)
* **응답**
<br>![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F1VUrj%2FbtszkyCdtMN%2FjqNIrIiq9KkCKak6oDGXn1%2Fimg.png)

### 6. 여정 등록
* **요청**
<br>![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbxhb9C%2Fbtszln8aA9j%2FgYEjoaqkzUYCy0qgBRniI0%2Fimg.png)
* **응답**
<br>![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FMlcLc%2FbtsziQw4dfe%2FSR29K5JPx8ZzViZIYQ3jVk%2Fimg.png)

### 7. 여정 정보 수정
### 7-1. 숙박정보
* **요청**
<br>![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FszjHW%2FbtszoikHMBb%2FOmlUhxbBw5WgkQMlmO1ke0%2Fimg.png)
* **응답**
<br>![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F0s7Td%2FbtszlIEdAht%2FFmTPlJK0IGyPQZnvpBVFk1%2Fimg.png)
### 7-2. 이동정보
* **요청**
<br>![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcOUIGy%2Fbtszmy2ptC1%2FwdVoenBVqhlrPaYFaM3XCK%2Fimg.png)
* **응답**
<br>![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Foy2ro%2FbtsznCKmt0m%2FLYWu63TKfHqTXMk0Ua0hH0%2Fimg.png)
### 7-3. 방문정보
* **요청**
<br>![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FPh3Wl%2Fbtszj4nZ7cD%2FuYdjdgDRk9ekjKKUBkPwb0%2Fimg.png)
* **응답**
<br>![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcuGZNK%2Fbtszl0Sgqgw%2F2uQTDYe9TdIGMcq29JP07k%2Fimg.png)
