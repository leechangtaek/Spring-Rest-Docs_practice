## 간단소개 
환자 정보에 대한 기본적인 CRUD API 개발

## 개발기간 
2023/06/01 프로젝트 세팅 및 H2 설정 완료
<br/><br/> 2023/06/06 ~ 2023/06/08 Entity 클래스 생성 및 CRUD API 개발

## 개발 환경
Project - Gradle - Groovy
<br/><br/> Language - Java11(OpenJdk version 11.0.18)
<br/><br/> Spring Boot 2.7.12

## 개발 내용
1.환자등록 - @PostMapping("/patient") 
<br/><br/> - CreatePatientRequest Dto로 환자의 정보를 받고, generatePatientNo 라는 메서드로 환자등록번호를 생성 후 환자를 등록합니다.
<br/><br/> 2.환자수정 - @PutMapping("/patient") 
<br/><br/> - EditPatientRequest Dto로 환자의 정보를 받아 수정합니다.
<br/><br/> 3.환자삭제 - @DeleteMapping("/patient/{patientId}") 
<br/><br/> - id값을 파라미터로 받은 후 있으면 삭제합니다.
<br/><br/> 4.환자조회 - @GetMapping("/patient/{patientId}") 
<br/><br/> - id값을 파라미터로 받은 후 있으면 querydsl을 사용해 내원정보와 환자의 정보를 가져옵니다.
<br/><br/> 5.환자목록조회 - @GetMapping("/patients") 
<br/><br/> - pageSize와 동적 검색 조건을 받은 후 querydsl을 사용해 페이징 처리된 환자목록 정보를 가져옵니다.

## Dependency
Spring Web
<br/><br/>JPA
<br/><br/>querydsl
<br/><br/>h2
<br/><br/>lombok


