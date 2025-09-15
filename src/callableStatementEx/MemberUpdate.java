package callableStatementEx;

import util.DBUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class MemberUpdate {

    private static final Connection CONN = DBUtil.getConnection();

    public static void main(String[] args) {
        String userID = "apple";
        int menuOption = 1;
        String newInfo = "12345678";
        String sql = "{call SP_MEMBER_UPDATE(?, ?, ?)}";

        try (CallableStatement call = CONN.prepareCall(sql)) {
            call.setString(1, userID);
            call.setInt(2, menuOption);
            call.setString(3, newInfo);
            call.executeUpdate();

            System.out.println("회원정보 수정이 완료되었습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
