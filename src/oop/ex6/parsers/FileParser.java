package oop.ex6.parsers;

import oop.ex6.Parser;
import oop.ex6.Regex;
import java.util.LinkedList;

public class FileParser {

	private final static int GLOBAL = 0;
	private final static int METHOD = 1;
	private final static int IF_WHILE = 2;

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
				if (scope == METHOD) {
					parser = new MethodParser(parser);
				}
				if (scope >= IF_WHILE){
					parser.addLine("{");
					parser = new IfWhileParser(parser);
				}
				parser.getParentParser().addChildParsers(parser);
				parser.addLine(line);
				continue;
			}
			if (line.equals("}")) {
				scope--;
				if (scope < GLOBAL) {
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
