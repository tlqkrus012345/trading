
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`
(
    `id`           BIGINT        NOT NULL AUTO_INCREMENT COMMENT '회원 시퀀스',
    `name`         VARCHAR(20)   NOT NULL COMMENT '회원 닉네임',
    `phone`        VARCHAR(15)   NOT NULL COMMENT '회원 핸드폰 번호',
    `point`        BIGINT        NOT NULL DEFAULT 0 COMMENT '회원 포인트',
    `created_at`   DATETIME(6)   NOT NULL COMMENT '생성 날짜',
    `modified_at`  DATETIME(6)   NOT NULL COMMENT '수정 날짜',
    PRIMARY KEY (`id`),
    UNIQUE KEY (`phone`, `name`)
);

DROP TABLE IF EXISTS `point_transaction`;
CREATE TABLE `point_transaction`
(
    `id`                 BIGINT        NOT NULL AUTO_INCREMENT COMMENT '포인트 이력 번호',
    `member_id`          BIGINT        NOT NULL COMMENT '회원 ID',
    `point`              BIGINT        NOT NULL COMMENT '충전/사용 포인트',
    `transaction_type`   VARCHAR(20)   COMMENT '포인트 이력 구분',
    `transacted_at`      DATETIME(6)   NOT NULL COMMENT '포인트 이력 일시',
    PRIMARY KEY (`id`),
    KEY `idx_member_id` (`member_id`)
);

DROP TABLE IF EXISTS `inventory`;
CREATE TABLE `inventory`
(
    `id`            BIGINT        NOT NULL AUTO_INCREMENT COMMENT '인벤토리 ID',
    `member_id`     BIGINT        NOT NULL COMMENT '회원 ID',
    `item_id`       BIGINT        NOT NULL COMMENT '아이템 ID',
    `created_at`    DATETIME(6)   NOT NULL COMMENT '생성 날짜',
    `modified_at`   DATETIME(6)   NOT NULL COMMENT '수정 날짜',
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `item`;
CREATE TABLE `item`
(
    `id`            BIGINT         NOT NULL AUTO_INCREMENT COMMENT '아이템 ID',
    `name`          VARCHAR(20)    NOT NULL COMMENT '아이템 이름',
    `type`          VARCHAR(20)    NOT NULL  COMMENT '아이템 타입',
    `price`         INT            NOT NULL COMMENT '아이템 가격',
    `description`   VARCHAR(255)   COMMENT '아이템 설명',
    `created_at`    DATETIME(6)    NOT NULL COMMENT '생성 날짜',
    `modified_at`   DATETIME(6)    NOT NULL COMMENT '수정 날짜',
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock`
(
    `id`             BIGINT        NOT NULL AUTO_INCREMENT COMMENT '재고 ID',
    `inventory_id`   BIGINT        NOT NULL COMMENT '인벤토리 ID',
    `item_id`        BIGINT        NOT NULL COMMENT '아이템 ID',
    `quantity`       INT           NOT NULL COMMENT '재고 수량',
    `created_at`     DATETIME(6)   NOT NULL COMMENT '생성 날짜',
    `modified_at`    DATETIME(6)   NOT NULL COMMENT '수정 날짜',
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `item_sale_info`;
CREATE TABLE `item_sale_info`
(
    `id`             BIGINT        NOT NULL AUTO_INCREMENT COMMENT '판매 등록 ID',
    `member_id`      BIGINT        NOT NULL COMMENT '회원 ID',
    `inventory_id`   BIGINT        NOT NULL COMMENT '인벤토리 ID',
    `item_id`        BIGINT        NOT NULL COMMENT '아이템 ID',
    `member_name`    VARCHAR(20)   NOT NULL COMMENT '회원 닉네임',
    `item_name`      VARCHAR(20)   NOT NULL COMMENT '아이템 이름',
    `item_price`     INT           NOT NULL COMMENT '아이템 가격',
    `item_type`      VARCHAR(20)   NOT NULL  COMMENT '아이템 타입',
    `description`    VARCHAR(255)  COMMENT '아이템 설명',
    `quantity`       INT           NOT NULL COMMENT '재고 수량',
    `status`         VARCHAR(20)   NOT NULL COMMENT '판매 상태',
    `created_at`     DATETIME(6)   NOT NULL COMMENT '생성 날짜',
    `modified_at`    DATETIME(6)   NOT NULL COMMENT '수정 날짜',
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `item_transaction`;
CREATE TABLE `item_transaction`
(
    `id`             BIGINT        NOT NULL AUTO_INCREMENT COMMENT '아이템 거래 ID',
    `member_id`      BIGINT        NOT NULL COMMENT '회원 ID',
    `inventory_id`   BIGINT        NOT NULL COMMENT '인벤토리 ID',
    `item_id`        BIGINT        NOT NULL COMMENT '아이템 ID',
    `total_price`    INT           NOT NULL COMMENT '총 구매 가격',
    `total_quantity` INT           NOT NULL COMMENT '총 구매 수량',
    `status`         VARCHAR(20)   NOT NULL COMMENT '거래 상태',
    `ordered_at`     DATETIME(6)   NOT NULL COMMENT '주문 날짜',
    `created_at`     DATETIME(6)   NOT NULL COMMENT '생성 날짜',
    `modified_at`    DATETIME(6)   NOT NULL COMMENT '수정 날짜',
    PRIMARY KEY (`id`),
    KEY `idx_ordered_at` (`ordered_at`)
);

DROP TABLE IF EXISTS `item_search_keyword`;
CREATE TABLE `item_search_keyword`
(
    `id`             BIGINT        NOT NULL AUTO_INCREMENT COMMENT '아이템 검색 ID',
    `item_keyword`   VARCHAR(20)   NOT NULL COMMENT '아이템 이름',
    `item_count`     BIGINT        NOT NULL COMMENT '아이템 검색 횟수',
    PRIMARY KEY (`id`)
);