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
