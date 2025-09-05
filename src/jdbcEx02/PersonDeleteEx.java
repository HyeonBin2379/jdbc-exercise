package jdbcEx02;

import jdbcEx01.vo.Person;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
* DELETE: JDBC를 이용해서 데이터 삭제를 수행
* DELETE FROM 테이블명;  -> 해당 테이블의 모든 행을 삭제
* DELETE FROM 테이블명 WHERE id = 식별값;  -> 해당 식별값의 행만 삭제
* DELETE FROM person WHERE num = 1;     -> person 테이블의 PK num = 1인 행을 삭제
* String sql = "DELETE FROM person WHERE num = ?";
* */
public class PersonDeleteEx {

    public static void main(String[] args) {
        Connection conn = DBUtil.getConnection();

        // SQL 문 작성
        String sql = "DELETE FROM person WHERE num = ?";

        // PreparedStatement 생성하고 값 지정
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, 1);
            int rows = pstmt.executeUpdate();
            System.out.println("삭제된 행의 수 " + rows);

            String selectSql = "select id, name from person";
            ResultSet rs = pstmt.executeQuery(selectSql);
            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt(1));
                person.setName(rs.getString(2));
                System.out.println(person.getId() + " " + person.getName());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
