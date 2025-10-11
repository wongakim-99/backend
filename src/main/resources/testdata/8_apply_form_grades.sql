-- 1학년만 모집 (신입생 전용)
INSERT INTO applyform_grades (applyform_id, grades) 
SELECT af.id, 'FIRST_GRADE'
FROM applyforms af
WHERE af.club_id IN ('club-001', 'club-007');

-- 1,2학년 모집 (저학년 대상)
INSERT INTO applyform_grades (applyform_id, grades) 
SELECT af.id, grade_value
FROM applyforms af
CROSS JOIN (VALUES ('FIRST_GRADE'), ('SECOND_GRADE')) AS grades(grade_value)
WHERE af.club_id IN ('club-002', 'club-008');

-- 3,4학년 모집 (고학년 대상)  
INSERT INTO applyform_grades (applyform_id, grades) 
SELECT af.id, grade_value
FROM applyforms af
CROSS JOIN (VALUES ('THIRD_GRADE'), ('FOURTH_GRADE')) AS grades(grade_value)
WHERE af.club_id IN ('club-003', 'club-010');

-- 전체 학년 모집 (모든 학년 환영)
INSERT INTO applyform_grades (applyform_id, grades) 
SELECT af.id, grade_value
FROM applyforms af
CROSS JOIN (VALUES ('FIRST_GRADE'), ('SECOND_GRADE'), ('THIRD_GRADE'), ('FOURTH_GRADE')) AS grades(grade_value)
WHERE af.club_id IN ('club-004', 'club-006', 'club-011', 'club-012');

-- 실제 존재하는 동아리들로 변경된 학년별 모집 데이터
-- 1,2학년 모집 (저학년 대상)
INSERT INTO applyform_grades (applyform_id, grades) 
SELECT af.id, grade_value
FROM applyforms af
CROSS JOIN (VALUES ('FIRST_GRADE'), ('SECOND_GRADE')) AS grades(grade_value)
WHERE af.club_id IN ('club-016', 'club-017');

-- 3,4학년 모집 (고학년 대상)
INSERT INTO applyform_grades (applyform_id, grades) 
SELECT af.id, grade_value
FROM applyforms af
CROSS JOIN (VALUES ('THIRD_GRADE'), ('FOURTH_GRADE')) AS grades(grade_value)
WHERE af.club_id IN ('club-018');

-- 멋쟁이 사자처럼 전체 학년 모집
INSERT INTO applyform_grades (applyform_id, grades)
SELECT af.id, grade_value
FROM applyforms af
         CROSS JOIN (VALUES ('FIRST_GRADE'), ('SECOND_GRADE'), ('THIRD_GRADE'), ('FOURTH_GRADE')) AS grades(grade_value)
WHERE af.club_id IN ('club-020');
