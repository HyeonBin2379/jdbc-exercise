package exercise_v1.exception.user;

public class NotRegisteredUserException extends LoginException {

    public NotRegisteredUserException(String message) {
        super(message);
    }
}
