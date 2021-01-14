package oop.ex6.main;

public class IllegalSFile extends Exception {

	private static final String S_FILE_ILLEGAL_MSG = "s-file illegal: ";

	public IllegalSFile(String message) {
		super(S_FILE_ILLEGAL_MSG + message);
	}
}
