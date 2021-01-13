package oop.ex6.parsers;

public class InvalidException extends ParserException {
	public InvalidException(String message) {
		super(message);
	}

//	/**
//	 * Returns the detail message string of this throwable.
//	 * @return the detail message string of this {@code Throwable} instance (which may be {@code null}).
//	 */
//	@Override
//	public String getMessage() {
//		return "Unmatching value";
//	}
}
