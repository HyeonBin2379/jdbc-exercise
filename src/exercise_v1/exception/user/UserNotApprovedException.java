package exercise_v1.exception.user;

public class UserNotApprovedException extends RuntimeException {

    public UserNotApprovedException(String message) {
        super(message);
    }
}
