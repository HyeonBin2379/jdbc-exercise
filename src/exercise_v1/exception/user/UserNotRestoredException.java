package exercise_v1.exception.user;

public class UserNotRestoredException extends IllegalStateException {

    public UserNotRestoredException(String message) {
        super(message);
    }
}
