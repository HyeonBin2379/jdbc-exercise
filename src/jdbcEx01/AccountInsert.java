package jdbcEx01;

import util.DBUtil;

import java.sql.*;

public class AccountInsert {

    public static void main(String[] args) {
        try (Connection con = DBUtil.getConnection()) {
            System.out.println("Connection established!" + con);
            System.out.println("AutoCommit 상태: " + con.getAutoCommit());

            // 매개변수화된 SQL문
            String sql = "insert into accounts(ano, owner, balance) values(?, ?, ?)";
            // PreparedStatement 얻기
            PreparedStatement pstmt = con.prepareStatement(sql);
            // 값을 지정
            pstmt.setString(1, "1111-1212-3232-12121");
            pstmt.setString(2, "홍길동");
            pstmt.setInt(3, 100000);

            // SQL문 실행 후 결과값 처리
            int result = pstmt.executeUpdate();
            System.out.println("저장된 행의 수" + result);

            // 추가한 행의 bno 값 얻기
            String ano = "1111-1212-3232-12121";
            if (result == 1) {
                System.out.println("insert success!");
                String selectsql = "SELECT * FROM accounts WHERE ano = ?";

                try (PreparedStatement selectPsTmt = con.prepareStatement(selectsql)) {
                    selectPsTmt.setString(1, ano);

                    // 가장 최근에 추가된 행의 데이터를 조회한 후 출력
                    try (ResultSet rs = selectPsTmt.executeQuery()) {
                        if (rs.next()) {
                            System.out.println("계좌번호: " + rs.getString(1));
                            System.out.println("계좌주: " + rs.getString(2));
                            System.out.println("계좌총액: " + rs.getString(3) + "원");
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Connection failed!");
        }
        System.out.println("Connection closed!");
    }
}
