## 1
## user_id = 42, store_id = 7이라고 가정
INSERT INTO store_review (user_id, store_id, rating, content, created_at, updated_at)
VALUES (42, 7, 5, '너무 맛있어요!', NOW(), NOW())

## 2
SELECT user.name, user.email, user.phone_number, user.point, user.review
FROM user
WHERE user.id = 1;

## 3
## 진행중
SELECT misson.title,
	misson.content,
	store.name,
	user_mission.success
FROM user_mission
JOIN mission ON misson.id = user_misson.mission_id
JOIN store ON store.id = misson.store_id
WHERE user_misson.user_id = 1
  AND user_misson.success = 2     ## 2:진행중
ORDER BY user_misson.id DESC
LIMIT 10 OFFSET 0;

## 진행완료
SELECT misson.title,
	misson.content,
	store.name,
	user_mission.success
FROM user_mission
JOIN mission ON misson.id = user_misson.mission_id
JOIN store ON store.id = misson.store_id
WHERE user_misson.user_id = 1
  AND user_misson.success = 1     ## 1:완료
ORDER BY user_misson.id DESC
LIMIT 10 OFFSET 0;

## 4
SELECT
  store.name, 
  mission.remain_period,  
  mission.name,
  mission.money_lower_limit,
  mission.point,
  category.name      ## 가게 카테고리 이름
FROM user u
JOIN location lu
  ON lu.user_id = u.id      ## 위치
JOIN location ls
  ON ls.position = lu.position     ## 같은 위치의 가게 위치
 AND ls.store_id IS NOT NULL
JOIN store
  ON store.id = ls.store_id
 AND store.status = 1
JOIN category
  ON category.id = store.category_id
JOIN mission
  ON mission.store_id = store.id
WHERE u.id = 1
ORDER BY mission.remain_period ASC
LIMIT 10 OFFSET 0;