#<1번>
INSERT INTO reviews (
	user_id,
	store_id,
	content,
	rating,
	created_at,
	updated_at
)

VALUES (
	1, --리뷰 작성한 유저 id
	10, -- 리뷰 대상 가게 id
	'와 개맛있다 또 시켜먹을게요', --리뷰 내용
	5, --별점(1~5)
	NOW(6), --wkrtjd tlrks
	NOW(6) -- 수정 시간( 초기에는 작성 시간과 동일 )

)

-----------------

#<2번 - 마이페이지 내가 쓴 리뷰 조회 쿼리>
SELECT r.id, r.content, r.rating, r.created_at, s.name AS store_name
FROM reviews r JOIN stores s ON r.store_id = s.id
WHERE r.user_id = 1 -- 현재 로그인한 유저 id 넣기
ORDER BY r.created_at DESC; --최신순으로 내가 쓴 리뷰 보려고 정렬


#<1:1문의하기 쿼리>
INSERT INTO inquires(
	id,
	user_id,
	title,
	content,
	created_at,
	updated_at,
	status
)

VALUES(
	1,
	10,
	'알림 설정은 어떻게 꺼요?',
	'분명 공지사항 알림은 껐는데 계속 알림이 와요',
	NOW(6),
	NOW(6),
	'new' -- 새로 등록된 문의는 기본 new 상태
)


-------------


#<3번 - 내가 진행 중, 완료한 미션 쿼리>
SELECT 
    um.id              AS user_mission_id,
    m.title            AS mission_title,
    m.description      AS mission_description,
    m.reward_point,
    s.name             AS store_name,
    um.status          AS mission_status,
    um.received_at,
    um.completed_at
FROM user_missions um
JOIN missions m ON um.mission_id = m.id
JOIN stores s ON m.store_id = s.id
WHERE um.user_id = 10 
  AND um.status IN ('in_progress', 'completed')
ORDER BY um.received_at DESC
LIMIT 10 OFFSET 0;


--------------

#<4번 - 홈 화면 쿼리>
SELECT 
    m.id AS mission_id,
    m.title AS mission_title,
    m.description,
    m.reward_point,
    m.deadline,
    s.name AS store_name,
    l.name AS location_name,
    um.status AS user_status
FROM missions m
JOIN stores s ON m.store_id = s.id
JOIN locations l ON s.location_id = l.id
LEFT JOIN user_missions um 
       ON m.id = um.mission_id 
      AND um.user_id = 10   -- 현재 로그인한 유저 ID
WHERE l.name = '안암동'      -- 현재 선택된 지역
ORDER BY m.created_at DESC -- 최신순 정렬하여 하단 리스트 보이게끔
LIMIT 10 OFFSET 0;          -- 페이징 (10개씩, 첫 페이지)
