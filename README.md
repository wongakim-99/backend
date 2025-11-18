## 📅 프로젝트 소개
> **"흩어진 동아리 정보를 한곳에, 복잡한 리크루팅을 간편하게."**

<img width="1377" height="770" alt="image" src="https://github.com/user-attachments/assets/eddddf48-61cb-48cc-9fd5-a239e598058e" />


**똑똑(TtokTtok)** 은 상명대학교 내 분산된 동아리 모집 정보로 인한 학생들의 정보 접근성 문제와, 엑셀/수작업에 의존하던 동아리 운영진의 비효율적인 채용 프로세스를 해결하기 위한 **올인원 리크루팅 플랫폼**입니다.

* **For Students:** 키워드/분야별 상세 필터링을 통한 맞춤형 동아리 탐색 및 간편 지원
* **For Admins:** 노코드(No-code) 스타일의 지원서 생성, 칸반 보드 형태의 지원자 관리, 합격 여부 이메일 자동 발송 등 채용 전 과정 자동화

---

## ✨ Key Features (핵심 기능)

### 🏆 리크루팅 프로세스 자동화 (Core)
#### **동적 지원서 빌더 (Form Builder):**
<img width="1275" height="714" alt="image" src="https://github.com/user-attachments/assets/58ce2f17-441a-4a11-9b06-17e8f6c3ebb7" />



#### **지원자 파이프라인 관리:**
<img width="1273" height="711" alt="image" src="https://github.com/user-attachments/assets/4015cc19-c3df-47a3-b55c-8a46b6fc2b09" />

<img width="1271" height="711" alt="image" src="https://github.com/user-attachments/assets/c6cb4413-5ecc-45fa-90d9-8555da76d397" />

* `서류 접수` -> `면접 심사` -> `최종 발표` 단계별 상태 변경 시스템 구축
* 각 전형 단계 이동 시 **데이터 무결성 검증** 및 자동화 로직 적용

  
#### **대량 이메일 비동기 처리:**
    * 서류/면접 결과 통보 시 `JavaMailSender`와 비동기 처리를 활용하여 다수의 지원자에게 지연 없이 결과 메일 발송

### 🛡️ 사용자 및 데이터 관리
* **이중 권한 관리 시스템:** `ROOT_ADMIN`(총동아리연합회) > `CLUB_ADMIN`(동아리 회장) > `USER`(학생) 계층형 권한 설계 (`Spring Security`)
* **데이터 엑셀 추출:** 운영진의 편의를 위해 지원자 명단 및 정보를 엑셀(`Apache POI`)로 변환하여 다운로드 제공

---

## 🔥 Technical Challenges & Troubleshooting
<details>
<summary>1. 복잡한 동적 필터링 성능 최적화 (QueryDSL)</summary>
<br>

**[Problem]**
동아리 조회 시 '카테고리', '분과', '모집 여부', '정렬 조건' 등 6개 이상의 필터링 조건이 조합되어야 했습니다. 초기에는 JPQL과 if문을 사용했으나, 동적 쿼리 작성이 어렵고 가독성이 떨어지는 문제가 있었습니다.

**[Solution]**
**QueryDSL**을 도입하여 `BooleanExpression`을 활용한 모듈형 조건을 구성했습니다.
- `null` 체크를 통해 동적 조건을 안전하게 처리
- 컴파일 타임에 타입 안전성(Type-Safety) 확보
- 복잡한 쿼리 로직을 직관적인 메서드 체이닝으로 리팩토링하여 유지보수성 증대

> **[Code]** [ClubCustomRepositoryImpl.java 보러가기](./src/main/java/org/project/ttokttok/domain/club/repository/ClubCustomRepositoryImpl.java)
</details>

<details>
<summary>2. RDBMS에서 NoSQL 성격의 동적 데이터 처리 (Form Builder)</summary>
<br>

**[Challenge]**
동아리마다 지원서 양식(질문 개수, 타입, 필수 여부)이 전부 달라 고정된 DB 테이블 스키마로는 대응이 불가능했습니다.

**[Approach]**
MongoDB 같은 NoSQL 도입을 고려했으나, 프로젝트 규모와 트랜잭션 관리를 위해 PostgreSQL 을 유지하기로 결정했습니다. 대신 **JPA의 `AttributeConverter`와 JSON 직렬화**를 활용했습니다.

**[Implementation]**
- 지원서 질문 목록(`List<Question>`)을 JSON 문자열로 변환하여 DB의 `TEXT` 컬럼에 저장
- 조회 시에는 다시 객체로 자동 매핑하여, RDBMS의 안정성과 NoSQL의 유연함을 동시에 확보

> **[Code]** [Question.java (JSON 매핑) 보러가기](./src/main/java/org/project/ttokttok/domain/applyform/domain/json/Question.java)
</details>

<details>
<summary>3. 대량 메일 발송 비동기 처리</summary>
<br>

**[Issue]**
서류/면접 합격자 발표 시, 수십 명의 지원자에게 동기식으로 메일을 발송할 경우 서버 응답 지연(Latency) 문제가 발생했습니다.

**[Optimization]**
Spring의 `@Async` 비동기 처리를 적용하여 메일 발송 로직을 별도 스레드에서 실행되도록 분리했습니다. 이를 통해 클라이언트 응답 시간을 획기적으로 단축하고 사용자 경험(UX)을 개선했습니다.

> **[Code]** [EmailService.java 보러가기](./src/main/java/org/project/ttokttok/infrastructure/email/service/EmailService.java)
</details>

### 👨‍💻 Maintainers
| Role | Name | GitHub |
|:---:|:---:|:---:|
| Backend | 김가원 | [@wongakim-99](https://github.com/wongakim-99) |
| Backend | 김수민 | [@codemaker-kim](https://github.com/codemaker-kim) |
| Frontend | 임형준 | [@hyeongjun6364](https://github.com/hyeongjun6364) |
| Frontend | 차현우 | [@chahyunwoo00](https://github.com/chahyunwoo00) |
| Designer | 남현지 | - |
