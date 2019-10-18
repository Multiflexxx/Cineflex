package exception;

public class UnequalParameterLength extends Exception {
  public UnequalParameterLength() {
    super("Unequal length of two input Arrays that need to be equal in length");
  }
}
