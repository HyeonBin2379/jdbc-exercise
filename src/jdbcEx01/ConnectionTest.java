package jdbcEx01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionTest {
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
                con.setAutoCommit(false);

                Statement stmt = con.createStatement();
                int result = stmt.executeUpdate("insert into person(id, name) values (100, '홍길동')");
                if (result == 1) {
                    System.out.println("insert success!");
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver loaded failed!");
        } catch (SQLException e) {
            System.out.println("Invalid SQL Query!");
        }
        System.out.println("Connection closed");
    }
}
