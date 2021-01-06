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
			RemoveSpaces remove = new RemoveSpaces(line);
			if (!remove.commentOrEmpty()){
				fixedLines.add(remove.checkSpaces());
			}
		}
	}
}
