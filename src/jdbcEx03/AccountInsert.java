package jdbcEx03;

import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountInsert {

    public static void main(String[] args) {
        Connection conn = DBUtil.getConnection();
        String sql = "INSERT INTO accounts(ano, owner, balance) values(?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "1111-1212-3232-12121");
            pstmt.setString(2, "홍길동");
            pstmt.setInt(3, 100000);

            int rows = pstmt.executeUpdate();
            System.out.println("추가한 행 수: " + rows);

            String selectSql = "select * from accounts";
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
