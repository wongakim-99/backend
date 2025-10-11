-- 2. 동아리 데이터 30개 (다양한 카테고리, 타입, 모집상태, 모집대상)
INSERT INTO clubs (id, name, profile_img, summary, club_type, club_category, club_univ, custom_category, content, view_count, admin_id, created_at, updated_at) VALUES

-- 프로그래밍/개발 동아리 (CENTRAL/ACADEMIC)
('club-001', 'CCC', 'https://picsum.photos/400/300?random=1', '기독교 프로그래밍 동아리로 함께 성장하는 개발자 공동체입니다', 'CENTRAL', 'ACADEMIC', 'ENGINEERING', '프로그래밍', '기독교 가치관을 바탕으로 프로그래밍을 배우고 나누는 동아리입니다. 다양한 프로젝트 경험을 통해 실무 능력을 기르고, 함께 성장하는 개발자 공동체를 만들어갑니다.', 150, '6c983aff-55e7-44d9-8895-ab9e33187fcc', NOW(), NOW()),
('club-002', 'SM미공간', 'https://picsum.photos/400/300?random=2', '상명대학교 미래공간 디자인과 학생들의 창작 공간', 'DEPARTMENT', 'ARTS', 'DESIGN', '공간디자인', '미래공간 디자인에 대한 창의적 아이디어를 공유하고 실현하는 학과 동아리입니다. 건축, 인테리어, 전시 디자인 등 다양한 공간 디자인 프로젝트를 진행합니다.', 89, 'bc040a8e-1d9f-47f3-b570-15ec328b6813', NOW(), NOW()),
('club-003', 'CODECURE', 'https://picsum.photos/400/300?random=3', '코딩으로 세상의 문제를 해결하는 해커톤 전문 동아리', 'CENTRAL', 'ACADEMIC', 'ENGINEERING', '해커톤', '해커톤과 코딩 대회를 통해 실력을 향상시키고, 사회 문제 해결에 기여하는 솔루션을 개발하는 동아리입니다.', 203, '852897cc-e29a-481c-83a4-a2af28747ed2', NOW(), NOW()),
('club-004', '우리말가꿈이', 'https://picsum.photos/400/300?random=4', '한국어와 국문학의 아름다움을 탐구하는 문학 동아리', 'CENTRAL', 'CULTURE', 'ARTS', '국문학', '우리말과 국문학의 아름다움을 연구하고 창작 활동을 통해 한국 문화의 가치를 발견하는 동아리입니다.', 76, '8b8fa36a-7408-4996-9004-c814a48756cb', NOW(), NOW()),
('club-005', 'Questioners', 'https://picsum.photos/400/300?random=5', '궁금한 것들을 탐구하고 토론하는 철학 동아리', 'CENTRAL', 'ACADEMIC', 'ARTS', '철학토론', '다양한 철학적 주제들을 탐구하고 토론을 통해 사고의 깊이를 더하는 동아리입니다.', 134, '4c83b8f6-541d-4320-baec-09ccbc0903dc', NOW(), NOW()),

-- 예술/문화 동아리
('club-006', 'CHEEZE', 'https://picsum.photos/400/300?random=6', '치즈처럼 진한 감성의 밴드 동아리', 'CENTRAL', 'ARTS', 'ARTS', '밴드', '다양한 장르의 음악을 연주하며 정기 공연과 축제 무대를 통해 음악적 재능을 펼치는 밴드 동아리입니다.', 312, 'c0354eb8-2df9-4dd6-9181-44150cb56aa1', NOW(), NOW()),
('club-007', '같이가치투자', 'https://picsum.photos/400/300?random=7', '함께하는 가치투자 스터디 모임', 'CENTRAL', 'ACADEMIC', 'GLOBAL_AREA', '투자', '가치투자 철학을 바탕으로 경제를 공부하고 투자 전략을 연구하는 금융 동아리입니다.', 187, 'eeea284a-b4f6-4f2b-951c-fd958b1a2706', NOW(), NOW()),
('club-008', '다크니스', 'https://picsum.photos/400/300?random=8', '어둠 속에서 빛을 찾는 사진 동아리', 'CENTRAL', 'ARTS', 'DESIGN', '사진', '다양한 컨셉의 사진 촬영과 전시를 통해 시각적 스토리텔링을 탐구하는 사진 동아리입니다.', 98, '9200a1f4-2eb2-4df7-bb64-242d767638f8', NOW(), NOW()),
('club-009', 'CRUNK BRAIN', 'https://picsum.photos/400/300?random=9', '창의적 사고로 브레인스토밍하는 기획 동아리', 'CENTRAL', 'CULTURE', 'CONVERGENCE_TECHNOLOGY', '기획', '창의적 아이디어 발굴과 기획 역량 강화를 위한 브레인스토밍 전문 동아리입니다.', 156, '1627483a-f790-4910-aec4-6ca75aa29c4a', NOW(), NOW()),
('club-010', '소울로', 'https://picsum.photos/400/300?random=10', '영혼이 담긴 힙합과 랩 음악 동아리', 'CENTRAL', 'ARTS', 'ARTS', '힙합', '힙합 문화와 랩 음악을 통해 자신만의 스타일을 표현하고 공연하는 음악 동아리입니다.', 245, '9eb2680e-1593-421f-82cd-ac0a69f1a27f', NOW(), NOW()),

-- 스포츠/건강 동아리
('club-011', 'Movement', 'https://picsum.photos/400/300?random=11', '몸과 마음이 건강한 운동 동아리', 'CENTRAL', 'SPORTS', 'ENGINEERING', '헬스', '다양한 운동을 통해 건강한 몸과 마음을 만들어가는 종합 스포츠 동아리입니다.', 289, '461a8581-7a94-4b12-9ea1-20183ef32b1e', NOW(), NOW()),
('club-012', 'RENEW', 'https://picsum.photos/400/300?random=12', '새로워지는 라이프스타일 동아리', 'CENTRAL', 'CULTURE', 'DESIGN', '라이프스타일', '건강한 라이프스타일과 자기계발을 통해 새로운 자신을 만들어가는 동아리입니다.', 167, 'eee361f9-b844-4fc4-8bf0-5bc4c6322dfb', NOW(), NOW()),
('club-013', 'Youth-JC', 'https://picsum.photos/400/300?random=13', '청년 기독교 문화 동아리', 'CENTRAL', 'VOLUNTEER', 'GLOBAL_AREA', '기독교', '젊은 기독교인들이 모여 신앙과 문화 활동을 함께하는 종교 동아리입니다.', 122, '0c9cb3df-604b-4267-b043-e6c134eea863', NOW(), NOW()),
('club-014', '블루버드', 'https://picsum.photos/400/300?random=14', '파란 하늘을 나는 자유로운 여행 동아리', 'CENTRAL', 'CULTURE', 'GLOBAL_AREA', '여행', '국내외 다양한 여행지를 탐방하며 견문을 넓히는 여행 전문 동아리입니다.', 198, '37040041-50c4-4ab5-a37c-0350c0b7a32e', NOW(), NOW()),
('club-015', 'ISSUE', 'https://picsum.photos/400/300?random=15', '사회 이슈를 다루는 시사 토론 동아리', 'CENTRAL', 'ACADEMIC', 'GLOBAL_AREA', '시사토론', '시사 문제와 사회 이슈에 대해 토론하고 분석하는 시사 동아리입니다.', 143, 'deb38f7b-cc8a-4d02-9f61-539fcc7c00b0', NOW(), NOW()),

-- 기술/창업 동아리
('club-016', 'FREEZE', 'https://picsum.photos/400/300?random=16', '시간을 정지시키는 마법같은 영상 제작 동아리', 'CENTRAL', 'ARTS', 'DESIGN', '영상제작', '창의적인 영상 콘텐츠 제작과 편집 기술을 배우고 작품을 만드는 영상 동아리입니다.', 176, '8dcc7795-440c-4c84-acc2-efda17373022', NOW(), NOW()),
('club-017', 'MindSet', 'https://picsum.photos/400/300?random=17', '마음가짐을 바꾸는 자기계발 동아리', 'CENTRAL', 'ACADEMIC', 'CONVERGENCE_TECHNOLOGY', '자기계발', '긍정적 마인드셋과 자기계발을 통해 성장하는 동아리입니다.', 189, 'bcc37279-c20a-48b1-928b-84c72b0d1366', NOW(), NOW()),
('club-018', 'ROUTE', 'https://picsum.photos/400/300?random=18', '길을 찾아 떠나는 등산 동아리', 'CENTRAL', 'SPORTS', 'ENGINEERING', '등산', '국내 명산을 등반하며 자연을 사랑하고 체력을 기르는 등산 동아리입니다.', 156, 'ff8065be-1b02-4574-922b-a776b9e32394', NOW(), NOW()),
('club-019', '벌크업', 'https://picsum.photos/400/300?random=19', '근육을 키우는 보디빌딩 동아리', 'CENTRAL', 'SPORTS', 'ENGINEERING', '보디빌딩', '체계적인 웨이트 트레이닝을 통해 근육을 발달시키고 건강한 몸을 만드는 동아리입니다.', 234, 'ea7fc21d-a634-40eb-b1c4-b562fffb9abf', NOW(), NOW()),
('club-020', '멋쟁이사자처럼', 'https://picsum.photos/400/300?random=20', '프로그래밍으로 세상을 바꾸는 IT 창업 동아리', 'CENTRAL', 'ACADEMIC', 'ENGINEERING', 'IT창업', '프로그래밍 교육과 창업 프로젝트를 통해 IT 인재를 양성하는 전국 연합 동아리입니다.', 387, '830172c4-a544-47c1-8d8f-0cba0fd207c6', NOW(), NOW()),

-- 문화/예술 동아리
('club-021', '상명유람단', 'https://picsum.photos/400/300?random=21', '상명대학교를 유람하며 캠퍼스 문화를 만드는 동아리', 'CENTRAL', 'CULTURE', 'GLOBAL_AREA', '캠퍼스문화', '캠퍼스 문화 행사 기획과 학교 홍보 활동을 하는 대학 문화 동아리입니다.', 201, 'f1a3e915-3cd8-4ce5-bd70-6e8e5bb94e7f', NOW(), NOW()),
('club-022', '실오라기', 'https://picsum.photos/400/300?random=22', '한 올 한 올 정성스럽게 만드는 수공예 동아리', 'CENTRAL', 'ARTS', 'DESIGN', '수공예', '뜨개질, 자수, 소품 만들기 등 다양한 수공예 작품을 만드는 창작 동아리입니다.', 87, '99409928-1cf6-44fa-9923-debbad392d32', NOW(), NOW()),
('club-023', '요쿡', 'https://picsum.photos/400/300?random=23', '요리하는 즐거움을 나누는 쿠킹 동아리', 'CENTRAL', 'CULTURE', 'CONVERGENCE_TECHNOLOGY', '요리', '다양한 요리를 배우고 나누며 음식 문화를 탐구하는 요리 동아리입니다.', 165, 'c3f6b67a-260a-4a2a-846d-0d123c8cfdcc', NOW(), NOW()),
('club-024', 'UNIT', 'https://picsum.photos/400/300?random=24', '하나로 연결된 댄스 유닛', 'CENTRAL', 'ARTS', 'ARTS', '댄스', '다양한 장르의 댄스를 배우고 공연하는 댄스 동아리입니다.', 298, '81ff176f-fed5-4926-9aee-fc1302c23bda', NOW(), NOW()),
('club-025', '아리아', 'https://picsum.photos/400/300?random=25', '아름다운 성악과 클래식 음악 동아리', 'CENTRAL', 'ARTS', 'ARTS', '성악', '클래식 음악과 성악을 통해 고급 음악 문화를 추구하는 동아리입니다.', 112, 'fe68d055-0f09-4c65-8ade-3c433f1deed4', NOW(), NOW()),

-- 봉사/사회 동아리
('club-026', 'HRC', 'https://picsum.photos/400/300?random=26', '인권을 보호하고 실천하는 인권 동아리', 'CENTRAL', 'VOLUNTEER', 'GLOBAL_AREA', '인권', '인권 보호와 사회 정의 실현을 위한 활동을 하는 사회 참여 동아리입니다.', 154, 'a8a60f3b-92e0-49eb-a007-11815e88eb95', NOW(), NOW());
