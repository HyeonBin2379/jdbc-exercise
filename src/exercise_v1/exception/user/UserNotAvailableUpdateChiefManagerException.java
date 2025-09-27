package exercise_v1.exception.user;

public class UserNotAvailableUpdateChiefManagerException extends UserNotHavePermissionException {

    public UserNotAvailableUpdateChiefManagerException(String message) {
        super(message);
    }
}
