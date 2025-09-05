package jdbcEx01;

import jdbcEx01.vo.Person;
import util.DBUtil;

import java.sql.*;

public class ConnectionDBUtilTest {

    public static void main(String[] args) {
        try {
            Connection con = DBUtil.getConnection();

            Statement stmt = con.createStatement();
            int result = stmt.executeUpdate("insert into person(id, name) values(1, '홍길동')");
            if (result == 1) {
                System.out.println("Insert successful");
            }

            String selectSql = "select * from person";
            ResultSet rs = stmt.executeQuery(selectSql);
            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                System.out.println(person);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Connection failed!");
        }
    }
}
