package oop.ex6;

import java.io.File;
import java.io.*;

public class SReader {

	private BufferedReader reader;

	public SReader(String sFileName) {
		try {
			reader = new BufferedReader(new FileReader(sFileName));
		} catch (FileNotFoundException e) {//error
			e.printStackTrace();
		}
	}

	String getNext() {
		try {
			return reader.readLine();
		} catch (IOException e) {//error
			e.printStackTrace();
			return null;
		}
	}
}
