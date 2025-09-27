package exercise_v1.exception.user;

public class IDNotFoundException extends LoginException {

    public IDNotFoundException(String message) {
        super(message);
    }
}
