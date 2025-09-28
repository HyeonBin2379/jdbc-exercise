package exercise_v1.exception.user;

public class UserNotRegisteredException extends IllegalStateException {

    public UserNotRegisteredException(String message) {
        super(message);
    }

}
