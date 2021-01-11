package oop.ex6.parsers;

import oop.ex6.Parser;
import oop.ex6.Regex;
import java.util.LinkedList;

public class FileParser {

	private int scope = 0;
	private final LinkedList<String> fixedLines;
	private Parser parser;

	public FileParser(LinkedList<String> lines) {
		fixedLines = lines;
		parser = GlobalParser.getInstance();
	}

	public void run() {
		for (String line : fixedLines) {
			Regex lineRegex = new Regex(line);
			if (lineRegex.enterScope()) { //check for "void"?
				scope++;
				if (scope == 1) {
					parser = new MethodParser(parser);
				}
				if (scope >= 2){
					parser.addLine("{"); // מוסיף את זה כדי לדעת שצריך לפתוח סקופ פנימי
					parser = new IfWhileParser(parser);
				}
				parser.getParentParser().addChildParsers(parser);
				parser.addLine(line);
				continue;
			}
			else if (line.equals("}")) {
				scope--;
				if (scope < 0) {
					return;//ERROR
				}
				parser = parser.getParentParser();
				continue;
			}
			if((line = lineRegex.validSuffix()) == null){
				return;//ERROR not valid suffix
			}
			parser.addLine(line);
		}
	}


}
