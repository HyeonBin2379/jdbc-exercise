package jdbcEX03;

import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountUpdate {

    public static void main(String[] args) {
        Connection connection = DBUtil.getConnection();

        // 매개변수화된 UPDATE SQL문 작성
        String sql = " UPDATE accounts SET " +
                "owner = ?, " +
                "balance = ? " +
                "WHERE ano = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "도우너");
            pstmt.setInt(2, 200000);
            pstmt.setString(3, "1111-1212-3232-12121");

            // SQL문 실행 - 데이터 추가/수정/삭제 시 executeUpdate() 호출
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " rows updated!");

            // 변경 사항 조회
            String selectSql = "select owner, balance from accounts";
            ResultSet rs = pstmt.executeQuery(selectSql);
            while (rs.next()) {
                System.out.println(rs.getString("owner") + " " + rs.getInt("balance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
