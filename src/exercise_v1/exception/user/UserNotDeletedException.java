package exercise_v1.exception.user;

public class UserNotDeletedException extends RuntimeException {

    public UserNotDeletedException(String message) {
        super(message);
    }
}
