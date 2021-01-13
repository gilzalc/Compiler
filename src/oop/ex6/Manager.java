package oop.ex6;

import oop.ex6.parsers.FileParser;
import oop.ex6.parsers.GlobalParser;
import oop.ex6.parsers.Parser;
import oop.ex6.parsers.ParserException;
import oop.ex6.regexs.Regex;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Manages the entire parsing process over the S-java file
 */
public class Manager {
	private final SReader linesSReader;
	private final LinkedList<String> fixedLines = new LinkedList<>();

	public Manager(SReader SReader) {
		linesSReader = SReader;
	}

	/**
	 * Runs the entire process of the file processing and checking
	 * @throws IOException - in case of illegal buffering
	 * @throws ParserException problem encountered while parsing
	 */
	public void run() throws IOException, ParserException {
		sJavafileOrganizer();
		FileParser fileParser = new FileParser(fixedLines);
		fileParser.run();
		GlobalParser globalParser = GlobalParser.getInstance();
		globalParser.checkLines();
		runMethodsParsers();
	}

	/**
	 * Method that buffers the lines, organize them a bit (removes the spaces and the irrelevant comments)
	 * @throws IOException
	 */
	private void sJavafileOrganizer() throws IOException {
		String line;
		while ((line = linesSReader.getNext()) != null) {
			Regex regex = new Regex(line);
			if (regex.commentOrEmpty()) {
				continue;
			}
			fixedLines.add(regex.checkSpaces());
		}
	}


	/**
	 * This method runs all of the method parsers of the file
	 * @throws ParserException illegal format encountered
	 */
	private void runMethodsParsers() throws ParserException {
		LinkedList<Parser> methodsParsers = GlobalParser.getInstance().getChildParsers();
		for (Parser parser : methodsParsers){
			parser.checkLines();
		}
	}
}
