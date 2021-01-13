package oop.ex6.main;

public class IllegalSFile extends Exception {

	public IllegalSFile(String message){
		super("s-file illegal: " + message);
	}
}
