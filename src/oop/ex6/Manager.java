package oop.ex6;

import java.util.LinkedList;

public class Manager {
	private final SReader linesSReader;
	private final LinkedList<String> fixedLines = new LinkedList<>();

	public Manager(SReader SReader){
		linesSReader = SReader;
	}

	public void run(){
		String line;
		while ((line = linesSReader.getNext()) != null){
			Regex remove = new Regex(line);
			if (!remove.commentOrEmpty()){
				fixedLines.add(remove.checkSpaces());
			}
		}
	}
}
