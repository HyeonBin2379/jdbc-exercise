package exercise_v1.exception.user;

public class UserIDNotFoundException extends IllegalStateException {

    public UserIDNotFoundException(String message) {
        super(message);
    }
}
