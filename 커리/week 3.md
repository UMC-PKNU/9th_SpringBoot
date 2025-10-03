# 📌 API 명세서

---

## 📑 API 인덱스

| Index  | Method | URL                                    | 기능                  |
|--------|--------|----------------------------------------|-----------------------|
| 00-01  | POST   | /users                                 | 회원가입              |
| 00-02  | GET    | /users/{userId}                        | 홈 화면 조회          |
| 01-01  | GET    | /missions                              | 미션 목록 조회        |
| 01-02  | GET    | /users/{userId}/missions               | 유저가 받은 미션 조회 |
| 01-03  | PATCH  | /users/{userId}/missions/{missionId}   | 유저가 미션 성공      |
| 02-01  | POST   | /reviews                               | 리뷰 작성             |

---

## 00-01 회원가입 (POST /users)

### Request Body
```json
{
  "email": "user@example.com",
  "password": "hashedPassword",
  "name": "홍길동",
  "phone": "010-1234-5678"
}
```

### Response Body 
```json
{
  "id": 1,
  "email": "user@example.com",
  "name": "홍길동",
  "phone": "010-1234-5678",
  "status": "active",
  "point": 0,
  "notification_enabled": true,
  "created_at": "2025-09-23T12:34:56",
  "updated_at": "2025-09-23T12:34:56"
}
```
## 00-02 홈 화면 조회 (GET /users/{userId})
### Response Body
```json
{
  "id": 1,
  "name": "홍길동",
  "point": 1200,
  "notification_enabled": true,
  "status": "active",
  "recent_missions": [
    {
      "mission_id": 10,
      "title": "리뷰 작성하기",
      "status": "in_progress",
      "deadline": "2025-10-01T23:59:59"
    }
  ],
  "recent_reviews": [
    {
      "review_id": 5,
      "store_name": "스타벅스 부산대점",
      "rating": 4,
      "content": "스벅은 실패가 없네",
      "created_at": "2025-09-20T11:00:00"
    }
  ]
}
```

## 01-01 미션 목록 조회 (GET /missions)
### Response Body
```json
{
  "missions": [
    {
      "id": 1,
      "store_id": 3,
      "title": "리뷰 작성하기",
      "description": "스타벅스 음료 후기를 남겨주세요",
      "reward_point": 100,
      "deadline": "2025-10-01T23:59:59"
    },
    {
      "id": 2,
      "store_id": 5,
      "title": "사진 인증 미션",
      "description": "매장에서 사진을 찍어 업로드하세요",
      "reward_point": 150,
      "deadline": "2025-10-05T23:59:59"
    }
  ]
}
```

## 01-02 유저가 받은 미션 목록 조회 (GET /users/{userId}/missions)
### Response Body
```json
{
  "user_id": 1,
  "missions": [
    {
      "mission_id": 10,
      "title": "리뷰 작성하기",
      "status": "in_progress",
      "deadline": "2025-10-01T23:59:59",
      "reward_point": 100
    },
    {
      "mission_id": 12,
      "title": "사진 인증 미션",
      "status": "expired",
      "deadline": "2025-09-10T23:59:59",
      "reward_point": 150
    }
  ]
}
```

## 01-03 유저 미션 성공 누르기 (PATCH /users/{userId}/missions/{missionId})
### Request
```json
PATCH /users/1/missions/10
Content-Type: application/json

{
  "status": "completed"
}
```
### Response Body
```json
{
  "mission_id": 10,
  "user_id": 1,
  "status": "completed",
  "completed_at": "2025-09-24T15:00:00"
}
```
## 02-01 리뷰 작성 (POST /reviews)
### Request Body
```json
{
  "user_id": 1,
  "store_id": 3,
  "rating": 4,
  "content": "스벅도 실패가 사실 있긴해"
}
```
### Response Body
```json
{
  "review_id": 5,
  "user_id": 1,
  "store_id": 3,
  "rating": 4,
  "content": "스벅도 실패가 사실 있긴해",
  "created_at": "2025-09-24T15:10:00"
}
```