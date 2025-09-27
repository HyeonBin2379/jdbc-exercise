package exercise_v1.exception.user;

public class NotUpdatedPassword extends LoginException {

    public NotUpdatedPassword(String message) {
        super(message);
    }
}
