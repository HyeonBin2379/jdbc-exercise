package jdbcEx02;

import jdbcEx01.vo.Person;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// update 테이블명 set (필드 = 수정값) where num = 1;
// update person set id = ?, name = ? where num = ?;
/* String sql = new StringBuilder()
        .append("UPDATE person ")
        .append("SET id = ?")
        .append(", name = ? ")
        .append("WHERE num = ?).toString();
* */
public class PersonUpdateEx {

    public static void main(String[] args) {
        Connection connection = DBUtil.getConnection();

        // 매개변수화된 UPDATE SQL문 작성
        String sql = new StringBuilder()
                .append(" UPDATE person SET ")
                .append("id = ?, ")
                .append("name = ? ")
                .append("WHERE num = ?").toString();

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, 150);
            pstmt.setString(2, "도우너");
            pstmt.setInt(3, 1);

            // SQL문 실행 - 데이터 추가/수정/삭제 시 executeUpdate() 호출
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " rows updated!");

            String selectSql = "select id, name from person";
            ResultSet rs = pstmt.executeQuery(selectSql);
            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt(1));
                person.setName(rs.getString(2));
                System.out.println(person.getId() + " " + person.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
