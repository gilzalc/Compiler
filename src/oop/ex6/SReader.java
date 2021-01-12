package oop.ex6;

import java.io.File;
import java.io.*;

public class SReader {

	private BufferedReader reader;

	public SReader(String sFileName) throws IOException {
//		try {
		reader = new BufferedReader(new FileReader(sFileName));
//		} catch (FileNotFoundException e) {//error
//			e.printStackTrace();
//		}
	}

	String getNext() throws IOException {
//		try {
		return reader.readLine();
//		} catch (IOException e) {//error
//			e.printStackTrace();
//			return null;
//		}
	}
}
