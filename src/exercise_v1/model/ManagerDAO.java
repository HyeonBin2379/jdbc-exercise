package exercise_v1.model;

import exercise_v1.constant.UserPage;
import exercise_v1.domain.Manager;
import exercise_v1.domain.User;
import util.DBUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class ManagerDAO implements UserDAO {

    private final Manager manager;

    public ManagerDAO(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void searchUserDetails() {
        // 현재 관리자 정보 조회
        UserPage.managerDetails(manager);
    }

    @Override
    public boolean updateUserInfo(User newInfo) {
        String sql = "{call manager_update(?, ?, ?, ?, ?, ?, ?)}";
        try (Connection conn = DBUtil.getConnection();
             CallableStatement call = conn.prepareCall(sql)) {
            call.setString(1, manager.getId());
            call.setString(2, newInfo.getPwd());
            call.setString(3, newInfo.getName());
            call.setString(4, newInfo.getPhone());
            call.setString(5, newInfo.getEmail());

            call.execute();

            // 현재 사용자 정보 갱신 -> 다음에 자신의 정보 조회할 때 반영해야 함
            manager.setPwd(newInfo.getPwd());
            manager.setName(newInfo.getName());
            manager.setPhone(newInfo.getPhone());
            manager.setEmail(newInfo.getEmail());

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteUserInfo() {
        return false;
    }
}
