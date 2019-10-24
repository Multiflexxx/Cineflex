package exception.registrierung;

public class UnmatchingPasswordException extends Exception {
    public UnmatchingPasswordException() {
        super("Entered passwords don't match");
    }
}
