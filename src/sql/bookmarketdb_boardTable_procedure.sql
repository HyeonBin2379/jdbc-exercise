use bookmarketdb;

-- 원활한 테스트를 위해 테이블 id 순번을 1로 초기화
truncate table boardtable;

-- 글생성 기능
DROP PROCEDURE IF EXISTS createBoard;
delimiter $$
create procedure createBoard(in btitle varchar(100), in bcontent text, in bwriter varchar(50))
begin
	set @btitle = btitle;
    set @bcontent = bcontent;
    set @bwriter = bwriter;
    
    set @insertQuery = 'insert into boardtable values(null, ?, ?, ?, now())';
    prepare myQuery from @insertQuery;
    execute myQuery using @btitle, @bcontent, @bwriter;
    
    deallocate prepare myQuery;
end $$
delimiter ;
call createBoard('새로운 게시글', '새로운 게시글 내용', '작성자');

-- 전체글 읽어오기 기능
DROP PROCEDURE IF EXISTS searchAll;
delimiter $$
create procedure searchAll(in boardtable varchar(50))
begin
    set @myBoard = concat('select * from ', boardtable);
    prepare myQuery from @myBoard;
    execute myQuery;
    
    deallocate prepare myQuery;
end $$
delimiter ;
call searchAll('boardtable');

-- 글번호 지정한 글 1개 읽어오기 기능
DROP PROCEDURE IF EXISTS searchOne;
delimiter $$
create procedure searchOne(in bno int)
begin
	set @bno = bno;
    
    set @selectQuery = 'select * from boardtable where bno = ?';
    prepare myQuery from @selectQuery;
    execute myQuery using @bno;
    
    deallocate prepare myQuery;
end $$
delimiter ;
call searchOne(1);

-- 글 수정 기능(제목, 내용 수정, 글쓴이도 변경 가능하다고 가정)
desc boardTable;
DROP PROCEDURE IF EXISTS updateBoard;
delimiter $$
create procedure updateBoard(in bno int, in btitle varchar(100), in bcontent text, in bwriter varchar(50))
begin
	set @bno = bno;
	set @btitle = btitle;
    set @bcontent = bcontent;
    set @bwriter = bwriter;

	-- 글쓴이도 변경 가능
	-- 게시글 수정 시 마지막 접근 시간 갱신
	set @updateQuery = 'update boardtable set btitle = ?, bcontent = ?, bwriter = ?, bdate = now() where bno = ?;';
    
    prepare myQuery from @updateQuery;
    execute myQuery using @btitle, @bcontent, @bwriter, @bno;
    
    deallocate prepare myQuery;
end $$
delimiter ;
call updateBoard(1, '게시글 변경', '변경된 게시글입니다.', '변경된 닉네임');

-- 글 삭제 기능
DROP PROCEDURE IF EXISTS deleteBoard;
delimiter $$
create procedure deleteBoard(in bno int)
begin
	set @bno = bno;
    set @deleteQuery = 'delete from boardtable where bno = ?';
    
    prepare myQuery from @deleteQuery;
    execute myQuery using @bno;
    
    deallocate prepare myQuery;
end $$
delimiter ;
call deleteBoard(1);