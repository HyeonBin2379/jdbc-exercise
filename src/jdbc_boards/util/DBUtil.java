package jdbc_boards.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/bookmarketdb?serverTimezone=Asia/Seoul";
    private static final String USERNAME = "bookadmin";
    private static final String PASSWORD = "bookadmin";

    static {
        try {
            Class.forName(DRIVER);
            System.out.println("드라이버 로딩 성공");
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
