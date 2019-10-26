package exception.registrierung;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException() {
        super("A account already exists for the entered Email");
    }
}
