## ✅ 홈 화면

| 항목      | 내용                     |
| ------- | ---------------------- |
| 메서드     | `GET`                  |
| API Endpoint      | `/users/{userId}/home` |
| Path variable | `userId`               |
| Query String      | 없음                     |
| Reuqest Body   | 없음                     |

### API Endpoint
```
/home/users/{userId}
/users/{userId}/home
```

두 방식 중에서 고민을 했는데 **사용자마다 홈 화면이 다르게 보이기 때문에**

홈 화면보다는 사용자가 먼저 나오는 게 맞다고 판단해서 두 번째 방식을 선택했다.



## ✅ 마이페이지 리뷰 작성
| 항목           | 내용                        |
| ------------ | ------------------------- |
| 메서드          | `POST`                    |
| API Endpoint           | `/users/{userId}/reviews` |
| Path variable     | `userId`                  |
| Query String         | 없음                        |
| Reuqest Body (JSON) | 아래 예시 참고                  |
| 비고           | `/home`은 화면 개념이므로 경로에서 제외 |

### API Endpoint
```
/users/{userId}/reviews
/users/{userId}/home/reviews
```

두 방식 중에서 고민을 했다.

UI를 기준으로 봤을 때 사용자 ID에 알맞는 홈에 접근 후 리뷰로 이동하기 때문에 두 번째 방식이 조금 더

타당하다고 생각했지만 **API는 UI가 아니라 리소스 모델을 따라가야 한다.**

두 번째 방식/home은 화면 개념으로 리소스 계층에서는 의미 없기 때문에 빼는 게 맞다고 생각했다.


### Request Body
``` json
{
  "storeId": 11,
  "content": "너무 맛있어요",
  "rating": "5.0",
  "image": "이미지URL",
  "status": "PUBLISHED"
}
```

## ✅ 미션 목록 조회
| 항목      | 내용                                                               |
| ------- | ---------------------------------------------------------------- |
| 메서드     | `GET`                                                            |
| API Endpoint      | `/users/{userId}/missions`                                       |
| Path variable | `userId`                                                         |
| Query String      | `status=IN-PROGRESS` (진행중) / `status=FAIL&status=SUCCESS` (진행완료) |
| Reuqest Body   | 없음                                                               |
| 비고      | 상태 컬럼은 **Enum** 사용: `IN_PROGRESS`, `SUCCESS`, `FAIL`             |

### 예시
- 진행중: /users/42/missions?status=IN_PROGRESS

- 진행완료: /users/42/missions?status=FAIL&status=SUCCESS

### 진행중인 미션 조회
```
Get /users/{userId}/missions?status=IN-PROGRESS
```
RESTful API 원칙에 따라 언더바(_) 대신 **하이폰(-) 사용**

## ✅ 미션 성공 처리
| 항목           | 내용                                     |
| ------------ | -------------------------------------- |
| 메서드          | `PATCH`                                |
| API Endpoint            | `/users/{userId}/missions/{missionId}` |
| Path variable      | `userId`, `missionId`                  |
| Query String           | 없음                                     |
| Reuqest Body (JSON) | 아래 예시 참고                               |
| 비고           | `PATCH`는 **변경 필드만** 포함 (ID 중복 전송 금지)   |

### 메서드
- 성공 여부 컬럼만 변경하면 되기 때문에 **PATCH 방식** 선택 (부분 수정)
- 전체 수정을 원할 경우 PUT 방식을 선택하면 된다.

### Request Body
``` json
{
  "status": "SUCCESS"
}
```
PATCH 방식의 경우 Request Body에는 바꿀 필드만 넣는 것이 원칙이다.

쿼리 파라미터 **`userId`, `missionId`**를 넣을 경우 중복만 발생하기 때문에 좋은 방법이 아니다.


## ✅ 회원가입
| 항목           | 내용                       |
| ------------ | ------------------------ |
| 메서드          | `POST`                   |
| API Endpoint           | `/users`                 |
| Path variable    | 없음                       |
| Query String           | 없음                       |
| Reuqest Body (JSON) | 아래 예시 참고                 |
| 비고           | 서버가 식별자 생성 (Path 변수 불필요) |

### 예시
``` json
{
  "name": "이름",
  "gender": 0,
  "birth": "YYYY-MM-DD",
  "address": "주소",
  "phone_number": "01012345678",
  "email": "example@example.com"
}
```

