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
		sfileOrganizer();
		FileParser fileParser = new FileParser(fixedLines);
		fileParser.run();
		try {
			runGlobalParser();
		} catch (IllegalFileFormat illegalFileFormat) {
			illegalFileFormat.printStackTrace();
		}
		runMethodsParsers();
		// parse method declarations
		// parse all scopes
	}

	private void sfileOrganizer() {
		String line;
		while ((line = linesSReader.getNext()) != null) {
			Regex regex = new Regex(line);
			if (!regex.commentOrEmpty()) {
				fixedLines.add(regex.checkSpaces());
			}
		}
	}

	private void runGlobalParser() throws IllegalFileFormat {
		GlobalParser globalParser = GlobalParser.getInstance();
		globalParser.checkLines();// declarations and assignments and creating methods
	}

	private void runMethodsParsers(){
		LinkedList<Parser> methodsParsers = GlobalParser.getInstance().getChildParsers();
		for (Parser parser : methodsParsers){
		}
	}

	private void runInnerParsers(){

	}
}
