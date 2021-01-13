package oop.ex6;

import oop.ex6.parsers.FileParser;
import oop.ex6.parsers.GlobalParser;
import oop.ex6.parsers.Parser;
import oop.ex6.parsers.ParserException;
import oop.ex6.regexs.Regex;

import java.io.IOException;
import java.util.LinkedList;

public class Manager {
	private final SReader linesSReader;
	private final LinkedList<String> fixedLines = new LinkedList<>();

	public Manager(SReader SReader) {
		linesSReader = SReader;
	}

	public void run() throws IOException, ParserException {
		sfileOrganizer();
		FileParser fileParser = new FileParser(fixedLines);
		fileParser.run();
//		try {
		GlobalParser globalParser = GlobalParser.getInstance();
		globalParser.checkLines();
//		} catch (IllegalSFile illegalSFile) {
//			illegalSFile.printStackTrace();
//		}
		runMethodsParsers();
//		if(!globalParser.getChildParsers().isEmpty()) {
//			runMethodsParsers();
//		}
		// parse method declarations
		// parse all scopes
	}

	private void sfileOrganizer() throws IOException {
		String line;
		while ((line = linesSReader.getNext()) != null) {
			Regex regex = new Regex(line);
			if (regex.commentOrEmpty()) {
				continue;
			}
			fixedLines.add(regex.checkSpaces());
		}
	}

	private void runGlobalParser() throws ParserException {
		GlobalParser globalParser = GlobalParser.getInstance();
		globalParser.checkLines();// declarations and assignments and creating methods
	}

	private void runMethodsParsers() throws ParserException {
		LinkedList<Parser> methodsParsers = GlobalParser.getInstance().getChildParsers();
		for (Parser parser : methodsParsers){
			parser.checkLines();
		}
	}

//	private void runInnerParsers(){
//
//	}
}
