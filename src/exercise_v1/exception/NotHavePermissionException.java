package exercise_v1.exception;

public class NotHavePermissionException extends RuntimeException {
    public NotHavePermissionException(String message) {
        super(message);
    }
}
