package exercise_v1.model.user;

import exercise_v1.domain.user.User;

public interface UserDAO {

    // 관리자, 일반회원 공통 기능 정의: 자신의 정보를 조회
    User searchUserDetails();
    boolean updateUserInfo(User newUserInfo);
    boolean deleteUserInfo();
}
