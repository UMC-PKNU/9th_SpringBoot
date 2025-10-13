# 1번
INSERT INTO Review (user_id, store_id, mission_id, content, rating, created_at)
VALUES (2060, 42, 55, '음 너무 맛있어요 포인트도 얻고 맛있는 맛집도 알게 된 것 같아 너무나도 행복한 식사였답니다. 다음에 또 올게요!', 5, NOW());

# 2번
SELECT
    h.id,
    h.nickname,
    h.email,
    h.phone_num,
    h.is_phone_num_ver,
    h.user_point
FROM User h
WHERE h.id = 2060;

# 3번
SELECT
    um.id,
    m.mission_title,
    m.mission_condition,
    s.store_name,
    um.status,
    um.started_at,
    um.completed_at
FROM UserMission um
JOIN Mission m ON um.mission_id = m.id
JOIN Store s ON m.store_id = s.id
WHERE um.user_id = 2060
  AND um.status IN (0, 1)
ORDER BY um.completed_at DESC
LIMIT 10 OFFSET 0;

# 4번
SELECT
    m.id,
    m.mission_title,
    m.mission_condition,
    m.reward_point,
    s.store_name,
    s.address,
    s.region,
    COALESCE(um.status, -1) # NULL 값을 -1로 치환하고, -1은 미진행을 뜻함
FROM Mission m
JOIN Store s ON m.store_id = s.id
LEFT JOIN UserMission um
    ON m.id = um.mission_id
    AND um.user_id = 2060 # 현재 로그인한 유저
WHERE s.region = ‘안암동’ # 선택된 지역을 뜻함
  AND m.start_date <= NOW()
  AND m.end_date >= NOW()
ORDER BY m.created_at DESC
LIMIT 10 OFFSET 0;