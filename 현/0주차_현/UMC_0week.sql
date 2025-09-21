CREATE TABLE `리뷰` (
	`id`	BIGINT	NOT NULL,
	`user_id`	BIGINT	NOT NULL,
	`mission_id`	BIGINT	NOT NULL,
	`store_id`	BIGINT	NOT NULL,
	`rating`	TINYINT	NOT NULL,
	`content`	TEXT	NULL	COMMENT '리뷰내용 공백가능',
	`created_at`	DATETIME(6)	NOT NULL,
	`updated_at`	DATETIME(6)	NOT NULL	DEFAULT LocalDateTime.now()
);

CREATE TABLE `음식카테고리` (
	`id`	BIGINT	NOT NULL,
	`name`	VARCHAR(20)	NOT NULL	DEFAULT NONE	COMMENT '한식, 중식, 일식 ...'
);

CREATE TABLE `유저선호음식` (
	`id`	INT	NOT NULL,
	`category_id`	INT	NOT NULL,
	`user_id`	BIGINT	NOT NULL
);

CREATE TABLE `회원약관동의여부` (
	`id`	BIGINT	NOT NULL,
	`terms_id`	BIGINT	NOT NULL,
	`user_id`	BIGINT	NOT NULL,
	`agreed`	BOOLEAN	NOT NULL	COMMENT '동의하면 1',
	`agreed_at`	DATETIME(6)	NOT NULL	DEFAULT LocalDateTime.now()
);

CREATE TABLE `가게` (
	`id`	BIGINT	NOT NULL,
	`category_id`	BIGINT	NOT NULL,
	`store_name`	VARCHAR(100)	NOT NULL,
	`address`	VARCHAR(255)	NOT NULL,
	`region`	VARCHAR(50)	NOT NULL,
	`created_at`	TIMESTAMP	NOT NULL,
	`updated_at`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `리뷰이미지` (
	`id`	BIGINT	NOT NULL,
	`review_id`	BIGINT	NOT NULL,
	`id2`	BIGINT	NOT NULL,
	`image_url`	VARCHAR(255)	NOT NULL
);

CREATE TABLE `미션` (
	`id`	BIGINT	NOT NULL,
	`store_id`	BIGINT	NOT NULL,
	`mission_title`	VARCHAR(100)	NOT NULL,
	`mission_condition`	VARCHAR(255)	NOT NULL,
	`reward_point`	INT	NOT NULL,
	`start_date`	DATETIME	NOT NULL,
	`end_date`	DATETIME	NOT NULL,
	`created_at`	DATETIME(6)	NOT NULL,
	`updated_at`	DATETIME(6)	NOT NULL	DEFAULT LocalDateTime.now()
);

CREATE TABLE `회원미션상황` (
	`id`	BIGINT	NOT NULL,
	`user_id`	BIGINT	NOT NULL,
	`mission_id`	BIGINT	NOT NULL,
	`status`	TINYINT	NOT NULL	DEFAULT 0	COMMENT '진행중 0 완료 1 실패 2',
	`started_at`	DATETIME	NOT NULL	DEFAULT LocalDateTime.now(),
	`completed_at`	DATETIME	NULL	COMMENT '완료 안 했을 수 있음',
	`auth_code`	VARCHAR(50)	NULL	COMMENT '인증번호 입력 안 할수도 있다'
);

CREATE TABLE `회원` (
	`id`	BIGINT	NOT NULL,
	`name`	VARCHAR(20)	NOT NULL,
	`nickname`	VARCHAR(20)	NULL	COMMENT '설정 안할 시 없는걸로',
	`gender`	TINYINT	NOT NULL	COMMENT '0 or 1 or 2',
	`birth_date`	DATE	NOT NULL,
	`address`	VARCHAR(100)	NOT NULL,
	`email`	VARCHAR(100)	NOT NULL	COMMENT 'UNIQUE',
	`phone_num`	VARCHAR(20)	NULL	COMMENT 'UNIQUE',
	`is_phone_num_ver`	BOOLEAN	NOT NULL	DEFAULT 0	COMMENT '동의 1 안함 0',
	`user_point`	INT	NOT NULL	DEFAULT 0,
	`user_state`	BOOLEAN	NOT NULL	DEFAULT 1	COMMENT '삭제시 0',
	`inactive_date`	DATETIME	NULL	COMMENT '삭제 안했으면 NULL',
	`created_at`	DATETIME(6)	NOT NULL,
	`updated_at`	DATETIME(6)	NOT NULL	DEFAULT LocalDateTime.now()
);

CREATE TABLE `이용약관` (
	`id`	BIGINT	NOT NULL,
	`name`	VARCHAR(100)	NOT NULL,
	`type`	BOOLEAN	NOT NULL	COMMENT '필수 0 선택 1'
);

ALTER TABLE `리뷰` ADD CONSTRAINT `PK_리뷰` PRIMARY KEY (
	`id`,
	`user_id`,
	`mission_id`,
	`store_id`
);

ALTER TABLE `음식카테고리` ADD CONSTRAINT `PK_음식카테고리` PRIMARY KEY (
	`id`
);

ALTER TABLE `유저선호음식` ADD CONSTRAINT `PK_유저선호음식` PRIMARY KEY (
	`id`,
	`category_id`,
	`user_id`
);

ALTER TABLE `회원약관동의여부` ADD CONSTRAINT `PK_회원약관동의여부` PRIMARY KEY (
	`id`,
	`terms_id`,
	`user_id`
);

ALTER TABLE `가게` ADD CONSTRAINT `PK_가게` PRIMARY KEY (
	`id`,
	`category_id`
);

ALTER TABLE `리뷰이미지` ADD CONSTRAINT `PK_리뷰이미지` PRIMARY KEY (
	`id`,
	`review_id`,
	`id2`
);

ALTER TABLE `미션` ADD CONSTRAINT `PK_미션` PRIMARY KEY (
	`id`,
	`store_id`
);

ALTER TABLE `회원미션상황` ADD CONSTRAINT `PK_회원미션상황` PRIMARY KEY (
	`id`,
	`user_id`,
	`mission_id`
);

ALTER TABLE `회원` ADD CONSTRAINT `PK_회원` PRIMARY KEY (
	`id`
);

ALTER TABLE `이용약관` ADD CONSTRAINT `PK_이용약관` PRIMARY KEY (
	`id`
);

ALTER TABLE `리뷰` ADD CONSTRAINT `FK_회원_TO_리뷰_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `회원` (
	`id`
);

ALTER TABLE `리뷰` ADD CONSTRAINT `FK_미션_TO_리뷰_1` FOREIGN KEY (
	`mission_id`
)
REFERENCES `미션` (
	`id`
);

ALTER TABLE `리뷰` ADD CONSTRAINT `FK_가게_TO_리뷰_1` FOREIGN KEY (
	`store_id`
)
REFERENCES `가게` (
	`id`
);

ALTER TABLE `유저선호음식` ADD CONSTRAINT `FK_음식카테고리_TO_유저선호음식_1` FOREIGN KEY (
	`category_id`
)
REFERENCES `음식카테고리` (
	`id`
);

ALTER TABLE `유저선호음식` ADD CONSTRAINT `FK_회원_TO_유저선호음식_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `회원` (
	`id`
);

ALTER TABLE `회원약관동의여부` ADD CONSTRAINT `FK_이용약관_TO_회원약관동의여부_1` FOREIGN KEY (
	`terms_id`
)
REFERENCES `이용약관` (
	`id`
);

ALTER TABLE `회원약관동의여부` ADD CONSTRAINT `FK_회원_TO_회원약관동의여부_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `회원` (
	`id`
);

ALTER TABLE `가게` ADD CONSTRAINT `FK_음식카테고리_TO_가게_1` FOREIGN KEY (
	`category_id`
)
REFERENCES `음식카테고리` (
	`id`
);

ALTER TABLE `리뷰이미지` ADD CONSTRAINT `FK_리뷰_TO_리뷰이미지_1` FOREIGN KEY (
	`review_id`
)
REFERENCES `리뷰` (
	`id`
);

ALTER TABLE `리뷰이미지` ADD CONSTRAINT `FK_리뷰_TO_리뷰이미지_2` FOREIGN KEY (
	`id2`
)
REFERENCES `리뷰` (
	`store_id`
);

ALTER TABLE `미션` ADD CONSTRAINT `FK_가게_TO_미션_1` FOREIGN KEY (
	`store_id`
)
REFERENCES `가게` (
	`id`
);

ALTER TABLE `회원미션상황` ADD CONSTRAINT `FK_회원_TO_회원미션상황_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `회원` (
	`id`
);

ALTER TABLE `회원미션상황` ADD CONSTRAINT `FK_미션_TO_회원미션상황_1` FOREIGN KEY (
	`mission_id`
)
REFERENCES `미션` (
	`id`
);

