package jdbc_members;

import com.mysql.cj.xdevapi.Type;
import util.DBUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    private final List<Member> members = selectAll();

    public boolean createMember(Member member) {
        String sql = "{call SP_MEMBER_INSERT(?, ?, ?, ?, ?)}";

        try(Connection conn = DBUtil.getConnection();
            CallableStatement call = conn.prepareCall(sql)) {
            // IN 파라미터 셋팅
            call.setString(1, member.getmUserID());
            call.setString(2, member.getmPwd());
            call.setString(3, member.getmEmail());
            call.setString(4, member.getmHp());

            // OUT 파라미터 셋팅
            call.registerOutParameter(5, Types.INTEGER);

            // 실행
            call.execute();

            int rtn = call.getInt(5);
            if (rtn == 100) {
                System.out.println("이미 가입된 사용자입니다.");
            } else {
                System.out.println("회원 가입이 완료되었습니다.");
            }
            return rtn == 200;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Member searchOne(String userID) {
        String sql = "{call SP_MEMBER_SEARCH(?)}";

        try (Connection conn = DBUtil.getConnection();
             CallableStatement call = conn.prepareCall(sql)) {
            call.setString(1, userID);
            try (ResultSet rs = call.executeQuery()) {
                if (rs.next()) {
                    Member member = new Member();

                    member.setmSeq(rs.getInt("m_seq"));
                    member.setmUserID(rs.getString("m_userid"));
                    member.setmPwd(rs.getString("m_pwd"));
                    member.setmEmail(rs.getString("m_email"));
                    member.setmHp(rs.getString("m_hp"));
                    member.setmRegistDate(rs.getDate("m_registDate"));
                    member.setmPoint(rs.getInt("m_point"));

                    return member;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Member> selectAll() {
        List<Member> members = new ArrayList<>();
        String sql = "{call SP_MEMBER_LIST()}";

        try (Connection conn = DBUtil.getConnection();
                CallableStatement call = conn.prepareCall(sql)) {
            ResultSet rs = call.executeQuery();

            while (rs.next()) {
                Member member = new Member();

                member.setmSeq(rs.getInt("m_seq"));
                member.setmUserID(rs.getString("m_userid"));
                member.setmPwd(rs.getString("m_pwd"));
                member.setmEmail(rs.getString("m_email"));
                member.setmHp(rs.getString("m_hp"));
                member.setmRegistDate(rs.getDate("m_registDate"));
                member.setmPoint(rs.getInt("m_point"));

                members.add(member);
            }
            return members;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    public boolean updateMember(Member newMember) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("변경할 정보를 선택하세요: 1.비밀번호 | 2.이메일 | 3.핸드폰번호");
        int menuOption = Integer.parseInt(br.readLine());

        String sql = "{call SP_MEMBER_UPDATE(?, ?, ?, ?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement call = conn.prepareCall(sql)) {

            call.setString(1, newMember.getmUserID());
            call.setInt(2, 1);
            call.registerOutParameter(4, Types.VARCHAR);

            switch (menuOption) {
                case 1:
                    System.out.println("비밀번호를 변경합니다.");
                    call.setString(3, newMember.getmPwd());
                    break;
                case 2:
                    System.out.println("이메일을 변경합니다.");
                    call.setString(3, newMember.getmEmail());
                    break;
                case 3:
                    System.out.println("핸드폰번호를 변경합니다.");
                    call.setString(3, newMember.getmHp());
                    break;
            }

            int affected = call.executeUpdate();
            if (affected > 0) {
                Member member = searchOne(newMember.getmUserID());
                int index = members.indexOf(member);
                members.set(index, member);
                System.out.println(call.getString(1));
                System.out.println("회원정보 수정이 완료되었습니다.");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteMember(String userID) {
        String sql = "{call SP_MEMBER_DELETE(?)}";

        try (Connection conn = DBUtil.getConnection();
                CallableStatement call = conn.prepareCall(sql)) {
            call.setString(1, userID);
            int affected = call.executeUpdate();

            if (affected > 0) {
                Member member = searchOne(userID);
                members.remove(member);
                System.out.println("회원 탈퇴가 완료되었습니다.");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
