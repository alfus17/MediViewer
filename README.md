
# 🧬 메디뷰어

### 📃 프로젝트 소개
> 의료 검사 기록 및 시리즈 사진을 직관적이고 편리하게 확인할 수 있는 웹 애플리케이션

### 📅 제작기간
> 2024.10.07 ~ 2024.11.15

### 🧑‍⚕️ 참여 인원
> |                    Name                    |  Position   |
> | :----------------------------------------: | :---------: |
> | [장광진](https://github.com/alfus17) | Back | 
> |     [김현수]()     | Front |
> |   [임현승]()    | Front |
> |     [양승혁]()     | Security |
> |     [김솔]()     | Design |


# 🚀 Stacks
<div>
<img src="https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white" alt="Oracle DB">
</div>
<div>
<img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot">
  <img src="https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white" alt="Spring Security">
  <img src="https://img.shields.io/badge/JPA-59666C?style=for-the-badge&logo=jpa&logoColor=white" alt="JPA">
  <img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white" alt="Thymeleaf">
</div>
<div>
  <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black" alt="JavaScript">
  <img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white" alt="HTML">
  <img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white" alt="CSS">
</div>
<div>
  <img src="https://img.shields.io/badge/SQL%20Developer-4479A1?style=for-the-badge&logo=oracle&logoColor=white" alt="SQL Developer">
  <img src="https://img.shields.io/badge/VS%20Code-007ACC?style=for-the-badge&logo=visualstudiocode&logoColor=white" alt="VS Code">
  <img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white" alt="Git"> 
</div>

---
# 📊 프로젝트 설계
###  간트차트
<details>
<summary>간트차트</summary>
<div markdown="1" style="padding-left: 15px;">
<img src="https://github.com/user-attachments/assets/f1fe365f-b2bf-48d5-88c0-d06744097eb8" width="800px"/>
</div>
</details>



### 주요 특징
- 직관적인 UI와 사용자 친화적 설계.
- 유저 관리 기능 제공 (권한 기반 접근 제어).
- 시리즈 사진의 확대 및 분석을 지원.

---

## 개발 환경
- **Frontend**: React (MUI), Cornerstone.js
- **Backend**: Spring Boot (JPA, Security)
- **Database**: OracleDB

---

## 역할 분담
- **장광진**: 팀장, 백엔드
- **김현수**: 백엔드
- **임현승**: 백엔드, 시큐리티
- **김솔**: 프론트엔드, 디자인
- **양승혁**: 프론트엔드

---

## 프로젝트 설계
### Entity Relationship Diagram (ERD)
데이터 구조를 효율적으로 관리하기 위해 설계된 ERD를 기반으로 구현.

### 간트 차트
프로젝트 개발 일정과 팀원들의 작업 분담을 명확히 정의.

---

## 주요 기능
1. **로그인 및 회원가입**
   - Spring Security를 활용하여 인증 및 권한 기반 페이지 접근 제어 구현.
   - 인증되지 않은 사용자는 `/login`으로 강제 이동.

2. **메인 페이지**
   - 검색 및 동적 렌더링 기능.
   - 검색 파라미터를 활용한 결과 필터링 및 렌더링.
   - Cornerstone 라이브러리를 사용해 이미지 시각화.

3. **상세 페이지**
   - `axios.all`을 사용해 데이터를 한번에 처리.
   - 검색 및 데이터 표시 최적화.

---

## 최적화
- 쿼리 최적화: 인터페이스 대신 기본 클래스를 사용해 성능 개선.
- 데이터 형식 통일: JPA로 날짜 비교 시 발생하는 오류 해결.

---

## 프로젝트의 장점
- 직관적 설계로 사용자가 쉽게 접근 가능.
- 관리 기능으로 유저 권한 설정 및 데이터 관리.

---

## 추후 보완점
- 코드 최적화 및 유지보수 용이성 향상.

---


