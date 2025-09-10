package jdbc_boards.model;

import jdbc_boards.vo.Board;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BoardDAO {

    private final List<Board> boards = searchAll();

    public boolean createBoard(Board board) {
        // 리스트에 이미 저장된 데이터가 존재하면 기존 리스트를 사용하여 아래 작업을 진행
        // 1. 쿼리 생성
        String sql = "INSERT INTO boardTable(btitle, bcontent, bwriter, bdate) VALUES(?, ?, ?, now())";

        // 2. DB와 연결한 후, insert 쿼리를 담아 DB서버로 요청하기 위한 PreparedStatement 생성
        // - 기본키가 auto_increment 옵션이 적용된 정수값이면, 아래와 같이 두번째 인자를 설정
        // - DBUtil.getConnection()은 싱글톤 객체를 반환
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt
                     = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // 3. SQL문에 사용할 실제 값 세팅
            pstmt.setString(1, board.getBtitle());
            pstmt.setString(2, board.getBcontent());
            pstmt.setString(3, board.getBwriter());

            // 4. 서버에서 처리된 insert 쿼리의 결과값을 처리
            synchronized (boards) {
                int affected = pstmt.executeUpdate();
                boolean ok = affected > 0;
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    // 생성된 PK를 Board 객체에 반영한 후 리스트에 저장
                    if (rs.next() && ok) {
                        board.setBno(rs.getInt(1));
                        boards.add(board);
                    }
                }
                return ok;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Board searchOne(int bno) {
        // 1. 쿼리 생성
        String sql = "SELECT * FROM boardTable WHERE bno = ?";

        // 2. Connection 성공 후, select 쿼리를 담아 DB서버로 요청하기 위한 PreparedStatement 생성
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // 3. SQL문에 사용할 실제 값 세팅
            pstmt.setInt(1, bno);

            // 4. 서버에서 처리된 select 쿼리의 결과값을 처리
            try (ResultSet rs = pstmt.executeQuery()) {
                // 검색된 테이블의 행에 관한 Board 객체를 반환
                if (rs.next()) {
                    int targetBno = rs.getInt("bno");
                    return boards.stream()
                            .filter(board -> targetBno == board.getBno())
                            .findAny()
                            .get();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Board> searchAll() {
        // 자바 코드에서 bno 순 내림차순 정렬 수행
        Comparator<Board> comparator = Comparator.comparingInt(Board::getBno).reversed();

        // boards에 데이터가 존재하면 정렬만 수행한 후 기존 리스트 반환
        if (boards != null && !boards.isEmpty()) {
            boards.sort(comparator);
            return boards;
        }

        // boards에 데이터가 존재하지 않으면 전체 조회 쿼리 실행
        List<Board> boardList = new ArrayList<>();
        String sql = "SELECT * FROM boardTable";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // 전체 조회 쿼리 실행 -> 조회 결과를 리스트로 변환
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Board board = new Board();

                board.setBno(rs.getInt("bno"));
                board.setBtitle(rs.getString("btitle"));
                board.setBcontent(rs.getString("bcontent"));
                board.setBwriter(rs.getString("bwriter"));
                board.setBdate(rs.getDate("bdate"));

                boardList.add(board);
            }
            // 리스트를 bno 기준으로 내림차순 정렬
            boardList.sort(comparator);

            return boardList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // DB에 데이터가 없는 경우에도 빈 리스트를 반환해야 함
        return boardList;
    }

    public boolean updateBoard(Board newBoard) {
        // 리스트에 이미 저장된 데이터가 존재하면 기존 데이터를 사용하여 아래 작업을 진행
        // 1. 쿼리 생성
        String sql = "UPDATE boardTable SET btitle = ?, bcontent = ?, bwriter = ?, bdate = now() WHERE bno = ?";

        // 2. DB와 연결 수행 및 update 쿼리를 담아 DB서버로 요청하기 위한 PreparedStatement 생성
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // 3. 값 세팅
            pstmt.setString(1, newBoard.getBtitle());
            pstmt.setString(2, newBoard.getBcontent());
            pstmt.setString(3, newBoard.getBwriter());
            pstmt.setInt(4, newBoard.getBno());

            // 4. 서버에서 처리된 update 쿼리의 결과값을 처리
            synchronized (boards) {
                int affected = pstmt.executeUpdate();
                if (affected > 0) {
                    // 리스트에서도 변경된 Board 객체를 찾아서 갱신
                    Board board = searchOne(newBoard.getBno());
                    int index = boards.indexOf(board);
                    boards.set(index, board);
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteBoard(int bno) {
        // 리스트에 저장된 데이터가 존재하면 기존 데이터를 사용하여 아래 작업을 진행
        // 1. 쿼리 생성
        String sql = "DELETE FROM boardTable WHERE bno = ?";

        // 2. DB 연결 및 update 쿼리를 담아 DB서버로 요청하기 위한 PreparedStatement 생성
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // 3. 값 세팅
            pstmt.setInt(1, bno);

            // 4. 서버에서 처리된 delete 쿼리의 결과값을 처리
            synchronized (boards) {
                int affected = pstmt.executeUpdate();
                if (affected > 0) {
                    // 리스트에서도 게시물을 삭제
                    Board board = searchOne(bno);
                    boards.remove(board);
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
