package exercise_v1.exception.user;

public class UserNotApprovedException extends IllegalStateException {

    public UserNotApprovedException(String message) {
        super(message);
    }
}
