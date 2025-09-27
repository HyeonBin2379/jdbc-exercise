package exercise_v1.exception.user;

public class UserNotUpdatedException extends RuntimeException {

    public UserNotUpdatedException(String message) {
        super(message);
    }
}
