package exercise_v1.model;

import exercise_v1.domain.Manager;
import exercise_v1.domain.User;

public class ManagerDAO implements UserDAO {

    private Manager manager;

    public ManagerDAO(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void searchUserDetails() {
    }

    @Override
    public boolean updateUserInfo(User newUserInfo) {
        return false;
    }

    @Override
    public boolean deleteUserInfo() {
        return false;
    }
}
