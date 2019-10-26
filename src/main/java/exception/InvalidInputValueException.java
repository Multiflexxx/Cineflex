package exception;

public class InvalidInputValueException extends Exception {
		public InvalidInputValueException() {
				super("Input Parameter passed to this function is not valid in the functions context");
		}
}
