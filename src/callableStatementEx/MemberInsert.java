package callableStatementEx;

import util.DBUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class MemberInsert {

    // 1. 드라이버 로딩 및 데이터 연결
    private static Connection CONN = DBUtil.getConnection();

    public static void main(String[] args) {
        String m_userID = "jenny1234";
        String m_pwd = "jenny1234";
        String m_email = "jenny1234@gmail.com";
        String m_hp = "010-1234-1234";
        String resultString = null;
        String sql = "{call SP_MEMBER_INSERT(?, ?, ?, ?, ?)}";

        try(CallableStatement call = CONN.prepareCall(sql)) {
            // IN 파라미터 셋팅
            call.setString(1, m_userID);
            call.setString(2, m_pwd);
            call.setString(3, m_email);
            call.setString(4, m_hp);

            // OUT 파라미터 셋팅
            call.registerOutParameter(5, Types.INTEGER);

            // 실행
            call.execute();

            int rtn = call.getInt(5);
            if (rtn == 100) {
                System.out.println("이미 가입된 사용자입니다.");
            } else {
                System.out.println("회원 가입이 완료되었습니다.");
            }
        } catch (SQLException e) {
        }
    }
}
