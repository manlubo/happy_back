# HappyGivers 고도화 - Back

---

## 프로젝트 목표

- **프론트엔드·백엔드 분리 아키텍처 구축**

  - Backend : Spring Boot
  - Frontend : Next.js + Vercel

- **RESTful API 설계 및 표준화**

  - 일관된 API 응답 포맷 적용
  - Swagger를 통한 API 문서화

- **데이터베이스 변경(MariaDB > PostgreSQL)**

  - 서비스 확장 및 안정성을 고려해 데이터 무결성, 확장성이 좋은 PostgreSQL로 변경

- **Database 마이그레이션**

  - Flyway 기반 DB 마이그레이션
  - 환경별 동일한 DB구조 유지 및 버전 관리

- **데이터 접근 계층 개선**

  - MyBatis > JPA 전환
  - DB 종속 최적화 및 객체 지향 접근

- **인증/인가 기능 확장**

  - 기존 세션 기반 인증 > JWT 기반 인증/인가(stateless)
  - HttpOnly 쿠키를 통한 JWT 저장

- **환경 분리 전략 수립**

  - 개발 / 운영 환경 명확한 분리
  - 설정 및 인프라 충돌 최소화

- **관측 가능성(Observability) 확보**

  - OpenTelemetry로 모니터링 도입
  - API 성능 병목 지점 분석 및 개선

---

## 프로젝트 관련 자료

- [요구사항 정의서 / 데이터베이스 설계(DB)](https://docs.google.com/spreadsheets/d/1hb1a63on8BZs6pdL26UP8kEoaUuD2vO8hOAk3Vmav2c/edit?usp=sharing)
- [데이터베이스 ERD](https://dbdiagram.io/d/happygivers_develop-68fe14a6357668b732a69bfb)

---

## 주요 기술 적용 및 개선내용

### OpenTelemetry

- 요청 추적 모니터링 (성능 개선 및 장애원인 파악)
- OpenTelemetry-JavaAgent기반 자동 계측
- 성능 영향 최소화를 위해 병목이 될 수 있는 로직, 비즈니스 로직에만 `@WithSpan` 적용
- 환경 분리 전략
  - 개발 환경 : 전체 Trace 수집
  - 운영 환경 : 5% Trace 샘플링
- 노이즈 트레이스를 필터링하여, 실제 API 요청과 연관된 DB 호출만 추적하도록 구성

### Flyway기반 DB 마이그레이션

- SQL 기반 마이그레이션을 위해 Flyway 도입
- 환경별 동일한 DB 구조 유지 및 스키마 변경 이력 관리

### 메일 전송 비동기 처리

- 메일 전송을 @Async로 비동기 처리하여 요청 스레드 점유 제거
- SMTP 외부 I/O 대기 제거로 API 응답 시간 개선(14364ms => 20ms)

### Security

- JWT 인증 필터는 토큰 검증과 SecurityContext 설정만 담당
- 인증 실패 응답은 `AuthenticationEntryPoint`에서 공통 처리하도록 책임 분리
- 필터와 인증 실패 처리의 역할을 명확히 분리하여 유지보수성과 확장성 확보
