package exception.registrierung;

public class UnmatchingPassword extends Exception {
    public UnmatchingPassword() {
        super("Entered passwords don't match");
    }
}
