# 리뷰 작성
# user_id: 4, store_id: 2로 가정
INSERT INTO store_review(user_id, store_id, rating, description)
VALUES (4, 2, 5, '맛있게 잘 먹었습니다.');

-- -
# 마이페이지
# user_id: 3

# user info
SELECT nickname, email, tel, point FROM user
WHERE id=3;

# review
SELECT * FROM store_review 
WHERE user_id = 3;

# inquiry
SELECT * FROM inquiry
WHERE user_id = 3;

-- -
# 미션 모아보기

# 진행 중
# user_id: 3

# OFFSET BASED PAGING
SELECT * FROM user_mission
WHERE user_id=3 AND status='IN_PROGRESS'
ORDER BY id DESC
LIMIT 10 OFFSET 0;

# CURSOR BASED PAGING
# 마지막 조회 미션 ID: 100
SELECT * FROM user_mission
WHERE user_id=3 AND status='IN_PROGRESS' AND id<100
ORDER BY id DESC
LIMIT 10;

# 진행 완료
# user_id: 3

# OFFSET BASED PAGING
SELECT * FROM user_mission
WHERE user_id=3 AND status='COMPLETED'
ORDER BY id DESC
LIMIT 10 OFFSET 0;

# CURSOR BASED PAGING
# 마지막 조회 미션 ID: 100
SELECT * FROM user_mission
WHERE user_id=3 AND status='COMPLETED' AND id<100
ORDER BY id DESC
LIMIT 10;
-- -
# 홈 화면
SELECT m.description, m.point, s.name, s.category FROM mission m
LEFT JOIN store s ON s.id = m.store_id
LEFT JOIN region r ON r.id = s.region_id
WHERE r.name='안암동' AND m.id < 10
ORDER BY m.id DESC
LIMIT 5;