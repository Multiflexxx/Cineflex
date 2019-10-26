package exception;

public class RequiredFactoryFailedException extends Exception{
    public RequiredFactoryFailedException() {
        super("Failed to get a required Object from a Factory");
    }
}
