use bookmarketdb;

CREATE TABLE TB_MEMBER (
                           m_seq INT AUTO_INCREMENT PRIMARY KEY,  -- 자동 증가 시퀀스
                           m_userid VARCHAR(20) NOT NULL,
                           m_pwd VARCHAR(20) NOT NULL,
                           m_email VARCHAR(50) NULL,
                           m_hp VARCHAR(20) NULL,
                           m_registdate DATETIME DEFAULT NOW(),  -- 기본값: 현재 날짜와 시간
                           m_point INT DEFAULT 0
);


-- 저장 프로시저 작성
-- 이미 테이블에 존재하는 중복 사용자는 예외 처리
-- 성공 시 숫자 200을, 이미 가입된 회원이면 100 반환
truncate table tb_member;
DROP PROCEDURE IF EXISTS SP_MEMBER_INSERT;
DELIMITER $$
CREATE PROCEDURE SP_MEMBER_INSERT(
    IN V_USERID VARCHAR(20),
    IN V_PWD VARCHAR(20),
    IN V_EMAIL VARCHAR(50),
    IN V_HP VARCHAR(20),
    OUT RTN_CODE INT
)
BEGIN
    DECLARE queried int;

    -- 테이블에 이미 저장된 데이터의 개수 확인
    select count(m_seq) into queried from TB_MEMBER where m_userid = V_USERID;

    -- 테이블에 저장된 데이터가 존재
    IF (queried > 0) then
        SET RTN_CODE = 100;
    -- 테이블에 저장된 데이터가 없음
    ELSE
#         INSERT INTO TB_MEMBER(M_USERID, M_PWD, M_EMAIL, M_HP, M_REGISTDATE)
#         VALUES(V_USERID, V_PWD, V_EMAIL, V_HP, NOW());

        SET @userID = V_USERID;
        SET @pwd = V_PWD;
        SET @email = V_EMAIL;
        SET @hp = V_HP;

        SET @strsql = concat_ws(' ',
                                'INSERT INTO',
                                'TB_MEMBER(m_userid, m_pwd, m_email, m_hp, m_registdate)',
                                'VALUES(?, ?, ?, ?, NOW())');
        PREPARE stmt FROM @strsql;
        EXECUTE stmt USING @userid, @pwd, @email, @hp;

        DEALLOCATE PREPARE stmt;
        SET RTN_CODE = 200;
    END IF;
    COMMIT;
END $$
DELIMITER ;

CALL SP_MEMBER_INSERT('apple', '1111', 'apple@sample.com', '010-1234-5678', @result);
select @result;
select * from TB_MEMBER;

-- 생성된 프로시저의 구조를 확인
show create procedure SP_MEMBER_INSERT;


-- 전체 회원 정보를 출력하는 프로시저
DROP PROCEDURE IF EXISTS SP_MEMBER_LIST;
DELIMITER $$
CREATE PROCEDURE SP_MEMBER_LIST()
BEGIN
    SET @strsql = 'SELECT * FROM TB_MEMBER';
    PREPARE STMT FROM @strsql;
    EXECUTE STMT;

    DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

CALL SP_MEMBER_LIST();


-- 회원 m_userID로 회원 정보 확인하기
DROP PROCEDURE IF EXISTS SP_MEMBER_SEARCH;
DELIMITER $$
CREATE PROCEDURE SP_MEMBER_SEARCH(IN V_USERID VARCHAR(20))
BEGIN
    SET @userID = V_USERID;
    SET @strsql = 'SELECT * FROM TB_MEMBER WHERE m_userid = ?';

    PREPARE stmt FROM @strsql;
    EXECUTE stmt USING @userID;

    DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

CALL SP_MEMBER_SEARCH('apple');


-- 회원정보 수정: 다중분기 처리(비밀번호 or 이메일 or 연락처 수정)
DROP PROCEDURE IF EXISTS SP_MEMBER_UPDATE;
DELIMITER $$
CREATE PROCEDURE SP_MEMBER_UPDATE(
    IN V_USERID VARCHAR(20),
    IN MENU_OPTION INT,
    IN V_INFO VARCHAR(50),
    OUT MSG VARCHAR(100))
BEGIN
    SET @userID = V_USERID;
    SET @updateQuery = 'UPDATE TB_MEMBER SET ';

    CASE
        WHEN MENU_OPTION = 1 THEN
            SET @pwd = V_INFO;
            SET @updateQuery = concat(@updateQuery, 'm_pwd = ? WHERE m_userid = ?');

            PREPARE stmt FROM @updateQuery;
            EXECUTE stmt USING @pwd, @userID;

            DEALLOCATE PREPARE stmt;

            SET MSG = '현재 회원의 비밀번호 변경이 완료되었습니다.';
        WHEN MENU_OPTION = 2 THEN
            SET @email = V_INFO;
            SET @updateQuery = concat(@updateQuery, 'm_email = ? WHERE m_userid = ?');

            PREPARE stmt FROM @updateQuery;
            EXECUTE stmt USING @email, @userID;

            DEALLOCATE PREPARE stmt;

            SET MSG = '현재 회원의 이메일 변경이 완료되었습니다.';
        WHEN MENU_OPTION = 3 THEN
            SET @hp = V_INFO;
            SET @updateQuery = concat(@updateQuery, 'm_hp = ? WHERE m_userid = ?');

            PREPARE stmt FROM @updateQuery;
            EXECUTE stmt USING @hp, @userID;

            DEALLOCATE PREPARE stmt;

            SET MSG = '현재 회원의 핸드폰번호 변경이 완료되었습니다.';
    END CASE;
END $$
DELIMITER ;

CALL SP_MEMBER_UPDATE('apple', 1, '12345678');

-- 회원정보 삭제: 지정한 id 삭제
DROP PROCEDURE IF EXISTS SP_MEMBER_DELETE;
DELIMITER $$
CREATE PROCEDURE SP_MEMBER_DELETE(IN V_USERID VARCHAR(20))
BEGIN
    SET @userID = V_USERID;
    SET @strsql = 'DELETE FROM TB_MEMBER WHERE m_userid = ?';

    PREPARE stmt FROM @strsql;
    EXECUTE stmt USING @userID;

    DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

CALL SP_MEMBER_DELETE('apple');