package exception.registrierung;

public class EmptyInputValueException extends Exception {
    public EmptyInputValueException() {
        super("Input value that can't be empty is empty");
    }
}
