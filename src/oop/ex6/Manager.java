package oop.ex6;

import oop.ex6.parsers.FileParser;
import oop.ex6.parsers.GlobalParser;

import java.util.LinkedList;

public class Manager {
	private final SReader linesSReader;
	private final LinkedList<String> fixedLines = new LinkedList<>();

	public Manager(SReader SReader) {
		linesSReader = SReader;
	}

	public void run() {
		readLines();
		FileParser fileParser = new FileParser(fixedLines);
		fileParser.run();
		GlobalParser globalParser = GlobalParser.getInstance();
		globalParser.checkLines();// declarations and assignments
		globalParser.createMethods();
		// parse method declarations
		// parse all scopes
	}

	private void readLines() {
		String line;
		while ((line = linesSReader.getNext()) != null) {
			Regex remove = new Regex(line);
			if (!remove.commentOrEmpty()) {
				fixedLines.add(remove.checkSpaces());
			}
		}
	}
}
