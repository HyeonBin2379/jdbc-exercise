package exercise_v1.exception;

public class UserNotRestoredException extends RuntimeException {

    public UserNotRestoredException(String message) {
        super(message);
    }
}
