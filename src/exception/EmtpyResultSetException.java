package exception;

import java.util.EmptyStackException;

public class EmtpyResultSetException extends Exception {
  public EmtpyResultSetException() {
    super("The ResultSet returned by the database Connector is empty");
  }
}
