# 1번
INSERT INTO review ( id, mem_id, store_id, content, rating, created_at)
VALUES ( [리뷰 id], [멤버 id], [가게 id], [리뷰 내용], 5, now());

# 2번
SELECT nickname, email, phone_number, score
FROM mem_info
WHERE id = [해당 유저id];

# 3번
SELECT s.store_name, m.mission_id, m.content, m.reward, mm.is_complete
FROM member_mission AS mm
INNER JOIN mission AS m ON mm.mission_id = m.id
INNER JOIN store AS s ON m.store_id = s.id
where mm.mem_id = [해당 id] and mm.is_complete = TRUE;

# 4번
SELECT s.located_at, s.store_type, s.store_name, mm.due_date, m.content, m.reward
FROM member_mission AS mm
INNER JOIN
	mission AS m ON mm.mission_id = m.id
INNER JOIN
	store AS s ON m.store_id = s.id
WHERE
	mm.mem_id = [현재 사용자 id]
	AND mm.is_complete = FALSE
	AND s.located_at LIKE ‘[선택된 지역]%’
ORDER BY
	mm.due_date ASC
LIMIT [페이지당 미션 수] OFFSET [건너뛸 미션 수];
