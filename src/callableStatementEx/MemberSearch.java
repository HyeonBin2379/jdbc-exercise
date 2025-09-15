package callableStatementEx;

import util.DBUtil;

import java.sql.*;

public class MemberSearch {

    private static final Connection CONN = DBUtil.getConnection();

    public static void main(String[] args) {
        String sql = "{call SP_MEMBER_SEARCH(?)}";

        try (CallableStatement call = CONN.prepareCall(sql)) {
            call.setString(1, "apple");
            ResultSet rs = call.executeQuery();

            if (rs.next()) {
                int m_seq = rs.getInt("m_seq");
                String m_userid = rs.getString("m_userid");
                String m_email = rs.getString("m_email");
                String m_hp = rs.getString("m_hp");
                Date m_registDate = rs.getDate("m_registDate");

                System.out.println("m_seq = " + m_seq);
                System.out.println("m_userid = " + m_userid);
                System.out.println("m_email = " + m_email);
                System.out.println("m_hp = " + m_hp);
                System.out.println("m_registDate = " + m_registDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
