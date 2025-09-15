package callableStatementEx;

import util.DBUtil;

import java.sql.*;

public class MemberDelete {

    private static final Connection CONN = DBUtil.getConnection();

    public static void main(String[] args) {
        String userID = "apple";
        String sql = "{call SP_MEMBER_DELETE(?)}";

        try (CallableStatement call = CONN.prepareCall(sql)) {
            call.setString(1, userID);
            call.executeUpdate();

            System.out.println("회원 탈퇴가 완료되었습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
