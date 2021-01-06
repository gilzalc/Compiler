package oop.ex6;

import java.io.File;
import java.io.*;

public class Reader {

	private BufferedReader reader;

	public Reader(String sFileName) {
		try {
			reader = new BufferedReader(new FileReader(sFileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	String getNext() {
		try {
			return reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
