package exercise_v1.exception.user;

public class UserNotHavePermissionException extends IllegalStateException {

    public UserNotHavePermissionException(String message) {
        super(message);
    }

}
