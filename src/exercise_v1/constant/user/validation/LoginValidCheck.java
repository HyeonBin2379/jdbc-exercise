package exercise_v1.constant.user.validation;

import exercise_v1.constant.user.InputMessage;
import exercise_v1.constant.user.LoginPage;
import exercise_v1.domain.user.User;
import exercise_v1.exception.user.LoginException;
import exercise_v1.exception.user.UserNotFoundException;
import exercise_v1.exception.user.UserNotRegisteredException;
import exercise_v1.exception.user.UserNotUpdatedException;

public class LoginValidCheck {

    private static final String LOGIN_MENU = "^[1-5]";

    public void checkMenuNumber(String menuOption) {
        if (!menuOption.matches(LOGIN_MENU)) {
            throw new IllegalArgumentException(InputMessage.INVALID_MENU_NUMBER.toString());
        }
    }

    public void checkLoginSuccess(User loginUser) {
        if (loginUser == null) {
            throw new LoginException(LoginPage.CANNOT_LOGIN.toString());
        }
    }

    public void checkUserRegistered(boolean registerACK) {
        if (!registerACK) {
            throw new UserNotRegisteredException(LoginPage.REGISTER_FAILED.toString());
        }
    }

    public void checkIDFound(boolean findIDACK) {
        if (!findIDACK) {
            throw new UserNotFoundException(LoginPage.NOT_FOUND_ID.toString());
        }
    }

    public void checkPwdUpdated(boolean pwdUpdateACK) {
        if (!pwdUpdateACK) {
            throw new UserNotUpdatedException(LoginPage.NOT_UPDATE_PASSWORD.toString());
        }
    }
}
