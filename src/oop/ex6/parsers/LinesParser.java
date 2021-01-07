package oop.ex6.parsers;

import oop.ex6.Parser;
import oop.ex6.Regex;

import java.util.LinkedList;

public class LinesParser {

	private int scope = 0;
	private LinkedList<String> fixedLines;
	private Parser scopeParser;

	public LinesParser(LinkedList<String> lines){
		fixedLines = lines;
		scopeParser = new GlobalParser();
	}

	public void run() {
		for (String line : fixedLines){
			Regex lineRegex = new Regex(line);
			if (lineRegex.enterScope()) {
				scope++;
				if (scope == 1) {
					scopeParser = new MethodParser(scopeParser);
					scopeParser.addLine(line);
				}
			}
			if (line.equals("}")){
				scope--;
				if (scope < 0){
					return;//ERROR
				}
				scopeParser = scopeParser.getParentParser();
				continue;
			}
			scopeParser.addLine(line);
		}
	}
}
