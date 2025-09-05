package jdbcEX03;

import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountSearch {

    public static void main(String[] args) {
        Connection connection = DBUtil.getConnection();
        String sql = "SELECT * FROM accounts WHERE ano = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "1111-1212-3232-12121");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String result = rs.getString("ano") + " " +
                        rs.getString("owner") + " " +
                        rs.getInt("balance");
                System.out.println(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
