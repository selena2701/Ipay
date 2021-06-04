package utils.exceptions;

public class UserExistsException extends Exception {
    public UserExistsException() {
        super("User exists");
    }
}
