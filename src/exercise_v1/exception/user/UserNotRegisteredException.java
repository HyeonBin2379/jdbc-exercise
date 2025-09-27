package exercise_v1.exception.user;

public class UserNotRegisteredException extends LoginException {

    public UserNotRegisteredException(String message) {
        super(message);
    }
}
