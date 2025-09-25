package exercise_v1.model;

import exercise_v1.domain.User;

public interface UserDAO {

    // 관리자, 일반회원 공통 기능 정의: 자신의 정보를 조회
    void searchUserDetails();
    boolean updateUserInfo(User newUserInfo);
    boolean deleteUserInfo();
}
