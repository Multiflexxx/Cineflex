package exception;

public class FailedObjectCreationException extends Exception {
  public FailedObjectCreationException() {
    super("Failed to create Object in Factory");
  }
}
