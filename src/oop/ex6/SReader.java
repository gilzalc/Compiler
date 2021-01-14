package oop.ex6;

import java.io.*;

/**
 * Represents the reader object of the program
 */
public class SReader {

	private final BufferedReader reader;

	public SReader(String sFileName) throws IOException {
		reader = new BufferedReader(new FileReader(sFileName));
	}

	String getNext() throws IOException {
		return reader.readLine();
	}
}
