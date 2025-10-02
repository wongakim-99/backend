-- 인덱스 삭제
DROP INDEX IF EXISTS idx_club_members_member_id;

-- 외래키 제거 (있다면)
ALTER TABLE club_members DROP CONSTRAINT IF EXISTS fk_club_members_member_id;

-- 컬럼 삭제
ALTER TABLE club_members DROP COLUMN member_id;