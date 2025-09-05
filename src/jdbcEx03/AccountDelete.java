package jdbcEx03;

import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountDelete {

    public static void main(String[] args) {
        Connection conn = DBUtil.getConnection();
        // SQL 문 작성
        String sql = "DELETE FROM accounts WHERE ano = ?";

        // PreparedStatement 생성하고 값 지정
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "1111-1212-3232-12121");
            int rows = pstmt.executeUpdate();
            System.out.println("삭제된 행의 수 " + rows);

            String selectSql = "select ano, owner, balance from accounts";
            ResultSet rs = pstmt.executeQuery(selectSql);
            while (rs.next()) {
                String row = new StringBuilder()
                        .append(rs.getString("ano"))
                        .append(" ")
                        .append(rs.getString("owner"))
                        .append(" ")
                        .append(rs.getInt("balance")).toString();
                System.out.println(row);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
