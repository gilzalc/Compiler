package oop.ex6;

import java.util.LinkedList;

public class Manager {
	private final Reader linesReader;
	private final LinkedList<String> fixedLines = new LinkedList<>();

	public Manager(Reader reader){
		linesReader = reader;
	}

	public void run(){
		String line;
		while ((line = linesReader.getNext()) != null){
			Regex toCheck = new Regex(line);
			if (!toCheck.commentOrEmpty()){
				fixedLines.add(toCheck.checkSpaces());
			}
		}
	}
}
