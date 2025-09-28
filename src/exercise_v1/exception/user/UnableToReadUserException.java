package exercise_v1.exception.user;

public class UnableToReadUserException extends IllegalStateException {

    public UnableToReadUserException(String message) {
        super(message);
    }

}
