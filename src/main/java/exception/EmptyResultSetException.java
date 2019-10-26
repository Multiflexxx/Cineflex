package exception;

public class EmptyResultSetException extends Exception {
  public EmptyResultSetException() {
    super("The ResultSet returned by the database Connector is empty");
  }
}
