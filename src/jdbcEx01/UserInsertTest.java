package jdbcEx01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserInsertTest {

    public static void main(String[] args) {
        String driverName = "com.mysql.cj.jdbc.Driver";
        // 'jdbc:mysql://' : jdbc를 통해 mysql 데이터베이스에 접근하기 위한 헤더
        String url = "jdbc:mysql://localhost:3306/bookmarketdb?serverTimezone=Asia/Seoul";
        String username = "root";
        String password = "mysql1234";

        try {
            Class.forName(driverName);
            System.out.println("Driver loaded successfully!");

            try (Connection con = DriverManager.getConnection(url, username, password)) {
                System.out.println("Connection established!" + con);
                System.out.println("AutoCommit 상태: " + con.getAutoCommit());

                // 매개변수화된 SQL문
                String sql = "insert into users(userid, username, userpassword, userage, useremail) values(?, ?, ?, ?, ?)";

                // PreparedStatement 얻기
                PreparedStatement pstmt = con.prepareStatement(sql);

                // 값을 지정
                pstmt.setString(1, "11");
                pstmt.setString(2, "이현빈");
                pstmt.setString(3, "1234");
                pstmt.setInt(4, 28);
                pstmt.setString(5, "user@gmail.com");

                // SQL문 실행 후 결과값 처리
                int result = pstmt.executeUpdate();
                System.out.println("저장된 행의 수" + result);
                if (result == 1) {
                    System.out.println("insert success!");
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver loaded failed!");
        } catch (SQLException e) {
            System.out.println("Invalid SQL Query!");
        }
        System.out.println("Connection closed!");
    }
}
