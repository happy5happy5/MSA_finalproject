# CREATE TABLE IF NOT EXISTS board
# (
#     id           BIGINT AUTO_INCREMENT PRIMARY KEY,
#     writer       VARCHAR(100) NOT NULL,
#     password     VARCHAR(40)  NOT NULL,
#     title        VARCHAR(300) NOT NULL,
#     content      TEXT         NOT NULL,
#     created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
#     updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
# );

CREATE TABLE IF NOT EXISTS user
(
    uid          BIGINT AUTO_INCREMENT PRIMARY KEY,
    userid       VARCHAR(100) NOT NULL UNIQUE,
    name         VARCHAR(100) NOT NULL,
    email        VARCHAR(100) NOT NULL,
    phone        VARCHAR(100) NOT NULL,
    password     VARCHAR(40)  NOT NULL,
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

# CREATE TABLE IF NOT EXISTS query
# (
#     qid          BIGINT AUTO_INCREMENT PRIMARY KEY,
#     userid       VARCHAR(100) NOT NULL,
#     title        VARCHAR(300) NOT NULL,
#     content      TEXT         NOT NULL,
#     hasfile      BOOLEAN   DEFAULT FALSE,
#     view         INT       DEFAULT 0,
#     created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
#     updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
#     foreign key (userid) references user (userid) on delete cascade on update cascade
# );

# CREATE TABLE IF NOT EXISTS comment
# (
#     cid          BIGINT AUTO_INCREMENT PRIMARY KEY,
#     qid          BIGINT       DEFAULT NULL,
#     userid       VARCHAR(100) DEFAULT NULL,
#     pid          BIGINT,
#     level        INT          DEFAULT 0,
#     deleted      BOOLEAN      DEFAULT FALSE,
#     content      TEXT NOT NULL,
#     created_time TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
#     foreign key (pid) references comment (cid) on update cascade,
#     foreign key (qid) references query (qid) on update cascade on delete cascade,
#     foreign key (userid) references user (userid) on update cascade
# );

# CREATE TABLE IF NOT EXISTS file_storage
# (
#     id          BIGINT AUTO_INCREMENT PRIMARY KEY,
#     userid      VARCHAR(100) DEFAULT NULL,
#     qid         BIGINT       DEFAULT NULL,
#     file_name   VARCHAR(255),
#     file_path   VARCHAR(255),
#     file_size   INT,
#     upload_time TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
#     foreign key (userid) references user (userid) on update cascade,
#     foreign key (qid) references query (qid) on update cascade on delete cascade
# );

# CREATE TABLE IF NOT EXISTS comment_reply
# (
#     id           BIGINT AUTO_INCREMENT PRIMARY KEY,
#     bid          BIGINT    DEFAULT NULL,
#     cid          BIGINT    DEFAULT NULL,
#     writer       VARCHAR(100) NOT NULL,
#     password     VARCHAR(40)  NOT NULL,
#     content      TEXT         NOT NULL,
#     created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
#     foreign key (bid) references board (id) on delete cascade on update cascade,
#     foreign key (cid) references comment (id) on delete cascade on update cascade
# );


# CREATE TABLE IF NOT EXISTS user_role
# (
#     uid  BIGINT DEFAULT NULL,
#     role VARCHAR(100) NOT NULL,
#     foreign key (uid) references user (uid) on delete cascade on update cascade
# );

# CREATE TABLE IF NOT EXISTS email_auth
# (
#     id           BIGINT AUTO_INCREMENT PRIMARY KEY,
#     email        VARCHAR(100) NOT NULL,
#     auth_key     VARCHAR(100) NOT NULL,
#     created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
# );


CREATE TABLE IF NOT EXISTS rs
(
    sur_seq      bigint AUTO_INCREMENT PRIMARY KEY COMMENT '설문번호 (PK)',
    sur_title    VARCHAR(200) NOT NULL COMMENT '설문제목',
    sur_desc     VARCHAR(200) COMMENT '설문설명',
    que_cnt      int COMMENT '질문수',
    sur_sat_date DATE COMMENT '설문시작일',
    sur_end_date DATE COMMENT '설문종료일',
    use_yn       VARCHAR(1) DEFAULT 'n' COMMENT '사용여부',
    hits         VARCHAR(8) DEFAULT '0' COMMENT '조회수',
    reg_name     VARCHAR(50) COMMENT '등록자',
    reg_date     DATE COMMENT '등록일',
    udt_name     VARCHAR(50) COMMENT '수정자',
    udt_date     DATE COMMENT '수정일'
);

CREATE TABLE IF NOT EXISTS rsi
(
    suri_seq   bigint AUTO_INCREMENT PRIMARY KEY COMMENT '문항번호 (PK)',
    sur_seq    bigint     NOT NULL COMMENT '설문번호 (FK)',
    suri_no    VARCHAR(8) NOT NULL COMMENT '문항번호 (1~)',
    suri_title VARCHAR(200) COMMENT '문항제목',
    suri_que1  VARCHAR(200) COMMENT '문항 보기1',
    suri_que2  VARCHAR(200) COMMENT '문항 보기2',
    suri_que3  VARCHAR(200) COMMENT '문항 보기3',
    suri_que4  VARCHAR(200) COMMENT '문항 보기4',
    suri_que5  VARCHAR(200) COMMENT '문항 보기5',
    suri_type  VARCHAR(2) DEFAULT '1' COMMENT '문항유형 (1:단일선택, 2:다중선택, 3:주관식)',
    suri_multi VARCHAR(2) DEFAULT '1' COMMENT '다중선택수 (1:1개, 2:2개, 3:3개, 4:4개, 5:5개)',
    suri_etc   VARCHAR(1) DEFAULT 'n' COMMENT '기타항목여부',
    reg_name   VARCHAR(50) COMMENT '등록자',
    reg_date   DATE COMMENT '등록일',
    udt_name   VARCHAR(50) COMMENT '수정자',
    udt_date   DATE COMMENT '수정일',
    INDEX suri_no_idx (suri_no)
);

CREATE TABLE IF NOT EXISTS rsa
(
    sura_seq    bigint AUTO_INCREMENT PRIMARY KEY COMMENT '답변번호 (PK)',
    sur_seq     bigint      NOT NULL COMMENT '설문번호 (FK)',
    suri_seq    bigint      NOT NULL COMMENT '문항번호 (FK)',
    sura_no     VARCHAR(8)  NOT NULL COMMENT '답변번호 (1~)',
    sura_item   VARCHAR(5) COMMENT '답변항목 (12345,24,123 등등) + 0:기타',
    suri_reason VARCHAR(200) COMMENT '선택사유 (12345일 경우)',
    sura_detail VARCHAR(200) COMMENT '답변내용 (0일 경우)',
    sura_name   VARCHAR(50) NOT NULL COMMENT '답변자',
    sura_date   DATE COMMENT '답변일',
    INDEX sura_no_idx (sura_no)
);

CREATE TABLE IF NOT EXISTS rsr
(
    surr_seq bigint AUTO_INCREMENT PRIMARY KEY COMMENT '답변번호 (PK)',
    sur_seq  bigint      NOT NULL COMMENT '설문번호 (FK)',
    surr_no  VARCHAR(8)  NOT NULL COMMENT '답변번호 (1~)',
    surr_cnt VARCHAR(8) COMMENT '답변수',
    surr_rate VARCHAR(8) COMMENT '답변비율',
    INDEX surr_no_idx (surr_no)
)