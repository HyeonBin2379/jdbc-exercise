package exercise_v1.exception;

public class NotUpdatedUserException extends RuntimeException {

    public NotUpdatedUserException(String message) {
        super(message);
    }
}
