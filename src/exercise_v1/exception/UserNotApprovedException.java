package exercise_v1.exception;

public class UserNotApprovedException extends RuntimeException {

    public UserNotApprovedException(String message) {
        super(message);
    }
}
