
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


# 🖥️ 기능 소개 및 시연
### 1. 로그인/회원가입
![image](https://github.com/user-attachments/assets/65a3b8bd-d947-45b9-aef7-a2c01e433440)



#### ⭐ 로그인 기능
> Spring Security를 활용하여 인증 및 권한 기반 페이지 접근 제어 구현. <br>
> 인증되지 않은 사용자는 `/login`으로 강제 이동.<br>

### 2. **메인 페이지**
![메인페이지 ](https://github.com/user-attachments/assets/88d6b70c-ea89-4468-a1fe-efe64240e9ab)
![다중검색](https://github.com/user-attachments/assets/5a73c093-4237-4c30-ae8a-d4a8750103ab)


#### ⭐ 메인페이지 기능
> 동적 쿼리문을 이용하여 여러가지 검색 조건을 통한 검색 가능하도록 구현. <br>
> 썸네일을 통하여 과거 검사 이미지를 미리 볼수있도록 구성 <br>
> 해당 환자의 과거 진료 기록내역들을 일괄적으로 확인 가능 <br>

### 3. **상세 페이지**
![디테일페이지1](https://github.com/user-attachments/assets/432b967f-7cb8-4c07-bd91-f4959b590d48)
![메인페이지2](https://github.com/user-attachments/assets/241c4847-5dac-4c20-b9f5-dcb23e2fb2ea)
![상세페이지3](https://github.com/user-attachments/assets/6c835ef1-79a2-4bf6-ac0d-27dc1cd74cbd)

#### ⭐ 상세페이지 기능
> `axios.all`을 사용해 비동기로 일괄적으로 데이터 요청 및 응답 받기<br>
> Conerstone API를 사용하여 여러가지 기능 및 렌더린 기능 구현<br>


---

## 프로젝트의 장점
> 직관적 설계로 사용자가 쉽게 접근 가능.
> 관리 기능으로 유저 권한 설정 및 데이터 관리.

---


