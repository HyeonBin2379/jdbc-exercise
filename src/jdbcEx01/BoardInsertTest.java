package jdbcEx01;

import java.io.FileInputStream;
import java.sql.*;

public class BoardInsertTest {

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
                String sql = "insert into boards(btitle, bcontent, bwriter, bdate, bfilename, bfiledata) values(?, ?, ?, now(), ?, ?)";

                // PreparedStatement 얻기
                // bno값은 mysql에서 insert될 때 1씩 증가 -> 두번째 인자를 통해 자동 증가되는 키값을 반환받음
                PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                // 값을 지정
                pstmt.setString(1, "게시글 테스트");
                pstmt.setString(2, "게시글 테스트 내용");
                pstmt.setString(3, "테스터");
                pstmt.setString(4, "spring.jpg");
                pstmt.setBlob(5, new FileInputStream("C:/Temp/spring.jpg"));

                // SQL문 실행 후 결과값 처리
                int result = pstmt.executeUpdate();
                System.out.println("저장된 행의 수" + result);

                // 추가한 행의 bno 값 얻기
                int bno = -1;
                if (result == 1) {
                    // ResultSet: 데이터 조회
                    try (ResultSet rs = pstmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            bno = rs.getInt(1);
                            System.out.println("bno = " + bno);
                        }
                    }
                }

                if (bno != -1) {
                    String selectsql = "SELECT bno, btitle, bcontent, bwriter, bdate, bfilename FROM boards WHERE bno = ?";
                    try (PreparedStatement selectPsTmt = con.prepareStatement(selectsql)) {
                        selectPsTmt.setInt(1, bno);

                        // 가장 최근에 추가된 행의 데이터를 조회한 후 출력
                        try (ResultSet rs = selectPsTmt.executeQuery()) {
                            if (rs.next()) {
                                bno = rs.getInt(1);
                                System.out.println("bno = " + bno);
                                System.out.println("btitle = " + rs.getString(2));
                                System.out.println("bcontent = " + rs.getString(3));
                                System.out.println("bwriter = " + rs.getString(4));
                                System.out.println("bdate = " + rs.getTimestamp(5));
                                System.out.println("bfilename = " + rs.getString(6));
                            }
                        }
                    }
                }
                if (result == 1) {
                    System.out.println("insert success!");
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver loaded failed!");
        } catch (Exception e) {
            System.out.println("Invalid SQL Query!");
        }
        System.out.println("Connection closed!");
    }
}
