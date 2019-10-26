package exception;

public class UnequalParameterLengthException extends Exception {
  public UnequalParameterLengthException() {
    super("Unequal length of two input Arrays that need to be equal in length");
  }
}
