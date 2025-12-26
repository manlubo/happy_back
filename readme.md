# HappyGivers 고도화 - Back

---

### 프로젝트 의도

- 프론트와 백의 분리 운영
- Restful한 Api 설계
- MariaDB > PostgreSQL로 변경(확장성 고려)
- DB마이그레이션 추가
- 데이터베이스 컬럼 수정(불필요한 컬럼 삭제, 컬럼&테이블 명 수정)
- 소셜로그인 기능 추가
- 마이바티스 > JPA 전환(DB 종속 최적화 및 객체 지향 접근)
- 운영 기능 개선(어드민 페이지 개선)
- 개발환경과 운영환경의 환경분리
- OpenTelemetry로 모니터링 추가(성능개선)

---

### 프로젝트 관련 자료

<a href="https://docs.google.com/spreadsheets/d/1hb1a63on8BZs6pdL26UP8kEoaUuD2vO8hOAk3Vmav2c/edit?usp=sharing" target="_blank">요구사항 정의서, 데이터베이스 설계(DB)</a>
<a href="https://dbdiagram.io/d/happygivers_develop-68fe14a6357668b732a69bfb" target="_blank">데이터베이스 ERD</a>

### OpenTelemetry

- 요청 추적 모니터링 (성능 개선 및 장애원인 파악)
- OpenTelemetry-JavaAgent 의존성 추가
- 느릴 수 있는 로직, 비즈니스 로직에만 @WithSpan 적용

### Flyway기반 DB 마이그레이션

- SQL문을 기반으로 DB 마이그레이션을 하기위해 Flyway를 사용
- 환경별 동일한 DB구조 유지가 목적
