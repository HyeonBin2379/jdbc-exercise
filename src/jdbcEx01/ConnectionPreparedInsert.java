package jdbcEx01;

import java.sql.*;

public class ConnectionPreparedInsert {
    public static void main(String[] args) {
        String driverName = "com.mysql.cj.jdbc.Driver";
        // 'jdbc:mysql://' : jdbc를 통해 mysql 데이터베이스에 접근하기 위한 헤더
        String url = "jdbc:mysql://localhost:3306/bookmarketdb?serverTimezone=Asia/Seoul";
        String username = "root";
        String password = "mysql1234";

        try {
            Class.forName(driverName);
            System.out.println("Driver loaded successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver loaded failed!");
        }

        try (Connection con = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connection established!" + con);
            System.out.println("AutoCommit 상태: " + con.getAutoCommit());
            con.setAutoCommit(true);

            // 매개변수화된 SQL문
            String sql = "insert into person(id, name) values(?, ?)";

            // PreparedStatement 얻기
            // PreparedStatement는 한번 생성되면 기존의 Statement 객체를 재활용
            PreparedStatement pstmt = con.prepareStatement(sql);

            // 값을 지정
            pstmt.setInt(1, 100);
            pstmt.setString(2, "신길동");

            // SQL문 실행 후 결과값 처리
            int result = pstmt.executeUpdate();
            System.out.println("저장된 행의 수" + result);
            if (result == 1) {
                System.out.println("insert success!");
            }
        } catch (SQLException e) {
            System.out.println("Invalid SQL Query!");
        }
        System.out.println("Connection closed");
    }
}
