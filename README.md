# app1_backup
 GS1 Digital Link Management System

# GS1 Digital Link Management System

GS1 표준 기반의 디지털링크 관리 시스템입니다.

## 기술 스택
- Frontend: Vue.js 3
- Backend: Spring Boot
- Database: MongoDB

## 주요 기능
- GS1 디지털링크 생성 및 관리
- GTIN, LOT, EXP 정보 관리
- 타겟 URL 관리 및 리디렉션

## 설치 및 실행 방법

### 백엔드 실행
bash
cd mydigitallink
./mvnw spring-boot:run


### 프론트엔드 실행
bash
cd frontend
npm install
npm run serve


### MongoDB 설정
- Database: mydigitallink
- Collection: gs1_links
