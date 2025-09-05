package jdbcEx01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Java -> DB 접근 방법(통신 프로그램과 큰 틀에서의 절차는 비슷함)
public class ConnectionEx {

    public static void main(String[] args) {
        Connection con = null;
        try {
            // 1. DB의 드라이버를 찾아서 로드 - MySQL JDBC 드라이버 등록
            // (리플렉션 기법을 활용하여 지정한 이름을 갖는 클래스를 로드)
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully!");

            // 2. DB 드라이버 로드 성공 시, Connection 객체를 생성하여 연결
            // DriverManager의 Connection 객체는 싱글톤
            // 기본 시간대는 UTC로 맞춰져 있기 때문에, 시간대를 조정해야 정상적으로 연결 가능
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmarketdb?serverTimezone=Asia/Seoul", "root", "mysql1234");
            System.out.println("Connection established!" + con);

            // 3. Connection 객체가 생성되었을 시, 쿼리문을 Statements 객체에 담아 DB에게 전송

            // 4. 전송한 결과를 받아서 처리
        } catch (Exception e) {
            System.out.println("Driver loaded failed!");
        } finally {
            // 5. 다 사용한 Statements와 Connection 객체를 닫음
            if (con != null) {
                try {
                    con.close();
                    System.out.println("Connection closed");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
