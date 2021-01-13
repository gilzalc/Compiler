package oop.ex6;

import java.io.*;

public class SReader {

	private final BufferedReader reader;

	public SReader(String sFileName) throws IOException {
		reader = new BufferedReader(new FileReader(sFileName));
	}

	String getNext() throws IOException {
		return reader.readLine();
	}
}
