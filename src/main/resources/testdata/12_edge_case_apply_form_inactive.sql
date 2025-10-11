-- 마감된 모집 (INACTIVE 상태) - recruiting: false가 되어야 함
INSERT INTO applyforms (id, title, sub_title, status, apply_start_date, apply_end_date, 
                       has_interview, interview_start_date, interview_end_date, max_apply_count, club_id, form_json, is_recruiting, created_at, updated_at)
VALUES
(gen_random_uuid(), '연극반 모집 마감', '모집이 마감되었습니다', 'INACTIVE', 
 '2025-02-01', '2025-02-15', false, null, null, 5, 'club-005', '[]', false, NOW(), NOW()),

(gen_random_uuid(), '실오라기 모집 마감', '모집이 마감되었습니다', 'INACTIVE',
 '2025-02-01', '2025-02-15', false, null, null, 5, 'club-022', '[]', false, NOW(), NOW()),

(gen_random_uuid(), '요쿡 모집 마감', '모집이 마감되었습니다', 'INACTIVE',
 '2025-02-01', '2025-02-15', false, null, null, 11, 'club-023', '[]', false, NOW(), NOW()),

(gen_random_uuid(), 'UNIT 모집 마감', '모집이 마감되었습니다', 'INACTIVE',
 '2025-02-01', '2025-02-15', false, null, null, 9, 'club-024', '[]', false, NOW(), NOW()),

(gen_random_uuid(), '아리아 모집 마감', '모집이 마감되었습니다', 'INACTIVE',
 '2025-02-01', '2025-02-15', false, null, null, 1, 'club-025', '[]', false, NOW(), NOW()),

(gen_random_uuid(), 'HRC 모집 마감', '모집이 마감되었습니다', 'INACTIVE',
 '2025-02-01', '2025-02-15', false, null, null, 10, 'club-026', '[]', false, NOW(), NOW()),

(gen_random_uuid(), '상명유람단 모집 마감', '모집이 마감되었습니다', 'INACTIVE',
 '2025-02-01', '2025-02-15', false, null, null, 13, 'club-021', '[]', false, NOW(), NOW());
