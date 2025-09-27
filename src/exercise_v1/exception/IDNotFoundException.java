package exercise_v1.exception;

public class IDNotFoundException extends LoginException {

    public IDNotFoundException(String message) {
        super(message);
    }
}
