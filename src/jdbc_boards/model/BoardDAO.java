package jdbc_boards.model;

import jdbc_boards.vo.Board;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {

    private List<Board> boards = new ArrayList<>();

    private Connection conn;

    public boolean createBoard(Board board) {
        // 1. Connection 수행
        if (boards.isEmpty()) {
            boards = searchAll();
        }

        conn = DBUtil.getConnection();
        // 2. 쿼리 생성
        String sql = "INSERT INTO boardTable(btitle, bcontent, bwriter, bdate) VALUES(?, ?, ?, now())";

        // 3. Connection 성공 후, insert 쿼리를 담아 DB서버로 요청하기 위한 PreparedStatement 생성
        // 기본키가 auto_increment 옵션이 적용된 정수값이면, 아래와 같이 두번째 인자를 설정
        try (PreparedStatement pstmt
                     = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // 4. 값 세팅
            pstmt.setString(1, board.getBtitle());
            pstmt.setString(2, board.getBcontent());
            pstmt.setString(3, board.getBwriter());

            // 5. 서버에서 처리된 insert 쿼리의 결과값을 처리
            int affected = pstmt.executeUpdate();
            boolean ok = affected > 0;
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                // 생성된 PK를 Board 객체에 반영
                if (rs.next()) {
                    board.setBno(rs.getInt(1));
                    boards.add(board);
                }
            }
            return ok;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Board searchOne(int bno) {
        conn = DBUtil.getConnection();
        String sql = "SELECT * FROM boardTable WHERE bno = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, bno);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Board board = new Board();

                    board.setBno(rs.getInt("bno"));
                    board.setBtitle(rs.getString("btitle"));
                    board.setBcontent(rs.getString("bcontent"));
                    board.setBwriter(rs.getString("bwriter"));
                    board.setBdate(rs.getDate("bdate"));

                    return board;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {conn.close();} catch (SQLException e) {}
            }
        }
        return null;
    }

    public boolean updateBoard(Board newBoard) {
        if (boards.isEmpty()) {
            boards = searchAll();
        }

        conn = DBUtil.getConnection();
        String sql = "UPDATE boardTable SET btitle = ?, bcontent = ?, bwriter = ? WHERE bno = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, newBoard.getBtitle());
            pstmt.setString(2, newBoard.getBcontent());
            pstmt.setString(3, newBoard.getBwriter());
            pstmt.setInt(4, newBoard.getBno());

            int affected = pstmt.executeUpdate();
            if (affected > 0) {
                Board board = searchOne(newBoard.getBno());

                int index = boards.indexOf(board);
                boards.set(index, board);
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {conn.close();} catch (SQLException e) {}
            }
        }
        return false;
    }

    public boolean deleteBoard(int bno) {
        conn = DBUtil.getConnection();
        String sql = "DELETE FROM boardTable WHERE bno = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bno);

            int affected = pstmt.executeUpdate();
            if (affected > 0) {
                Board board = searchOne(bno);
                boards.remove(board);
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {conn.close();} catch (SQLException e) {}
            }
        }
        return false;
    }

    public List<Board> searchAll() {
        conn = DBUtil.getConnection();
        List<Board> boardList = new ArrayList<>();
        String sql = "SELECT * FROM boardTable ORDER BY bno DESC";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
            return boardList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {conn.close();} catch (SQLException e) {}
            }
        }
        return boardList;
    }
}
