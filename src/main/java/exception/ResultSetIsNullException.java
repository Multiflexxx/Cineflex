package exception;

public class ResultSetIsNullException extends Exception {
  public ResultSetIsNullException() {
    super("ResultSet returned by Connector is null");
  }
}
