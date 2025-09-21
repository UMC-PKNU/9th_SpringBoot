CREATE TABLE `멤버-미션` (
	`mem_id`	bigint	NOT NULL	COMMENT '회원아이디',
	`mission_id`	bigint	NOT NULL,
	`is_accept`	boolean	NOT NULL	DEFAULT false,
	`is_complete`	boolean	NOT NULL	DEFAULT false,
	`due_date`	date	NULL
);

CREATE TABLE `가게` (
	`id`	bigint	NOT NULL,
	`store_name`	varchar(20)	NOT NULL	DEFAULT unkown,
	`store_type`	varchar(20)	NOT NULL,
	`located_at`	varchar(20)	NOT NULL
);

CREATE TABLE `알림` (
	`id`	bigint	NOT NULL,
	`mem_id`	bigint	NOT NULL	COMMENT '회원아이디',
	`alarm_type`	varchar(20)	NULL,
	`created_at`	date	NULL
);

CREATE TABLE `회원` (
	`id`	bigint	NOT NULL	COMMENT '회원아이디',
	`pwd`	varchar(20)	NOT NULL	COMMENT '회원 비밀번호'
);

CREATE TABLE `리뷰` (
	`id`	bigint	NOT NULL,
	`mem_id`	bigint	NOT NULL	COMMENT '회원아이디',
	`content`	varchar(100)	NULL,
	`rating`	int	NULL,
	`created_at`	date	NOT NULL,
	`Key`	VARCHAR(255)	NOT NULL
);

CREATE TABLE `미션` (
	`id`	bigint	NOT NULL,
	`content`	varchar(100)	NULL,
	`reward`	bigint	NULL,
	`local`	varchar(20)	NULL,
	`store_id`	bigint	NOT NULL
);

CREATE TABLE `회원정보` (
	`id`	bigint	NOT NULL	COMMENT '회원아이디',
	`nickanme`	varchar(20)	NOT NULL	COMMENT 'unique',
	`point`	bigint	NOT NULL	DEFAULT 0,
	`last_login_at`	date	NULL,
	`is_active`	boolean	NOT NULL,
	`inactive_date`	date	NULL,
	`created_at`	date	NOT NULL,
	`phone_number`	varchar(15)	NOT NULL,
	`e-mail`	varchar(20)	NOT NULL	COMMENT 'unique',
	`local`	varchar(20)	NULL
);

ALTER TABLE `멤버-미션` ADD CONSTRAINT `PK_멤버-미션` PRIMARY KEY (
	`mem_id`,
	`mission_id`
);

ALTER TABLE `가게` ADD CONSTRAINT `PK_가게` PRIMARY KEY (
	`id`
);

ALTER TABLE `알림` ADD CONSTRAINT `PK_알림` PRIMARY KEY (
	`id`,
	`mem_id`
);

ALTER TABLE `회원` ADD CONSTRAINT `PK_회원` PRIMARY KEY (
	`id`
);

ALTER TABLE `리뷰` ADD CONSTRAINT `PK_리뷰` PRIMARY KEY (
	`id`
);

ALTER TABLE `미션` ADD CONSTRAINT `PK_미션` PRIMARY KEY (
	`id`
);

ALTER TABLE `회원정보` ADD CONSTRAINT `PK_회원정보` PRIMARY KEY (
	`id`
);

ALTER TABLE `멤버-미션` ADD CONSTRAINT `FK_회원_TO_멤버-미션_1` FOREIGN KEY (
	`mem_id`
)
REFERENCES `회원` (
	`id`
);

ALTER TABLE `멤버-미션` ADD CONSTRAINT `FK_미션_TO_멤버-미션_1` FOREIGN KEY (
	`mission_id`
)
REFERENCES `미션` (
	`id`
);

ALTER TABLE `알림` ADD CONSTRAINT `FK_회원_TO_알림_1` FOREIGN KEY (
	`mem_id`
)
REFERENCES `회원` (
	`id`
);

ALTER TABLE `회원정보` ADD CONSTRAINT `FK_회원_TO_회원정보_1` FOREIGN KEY (
	`id`
)
REFERENCES `회원` (
	`id`
);

