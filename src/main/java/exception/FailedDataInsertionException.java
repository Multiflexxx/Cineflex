package exception;

public class FailedDataInsertionException extends Exception {
  public FailedDataInsertionException() {
    super("Faild to insert data into database");
  }
}
