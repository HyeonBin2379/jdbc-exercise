package exercise_v1.exception.user;

public class UserNotHavePermissionException extends RuntimeException {
    public UserNotHavePermissionException(String message) {
        super(message);
    }
}
