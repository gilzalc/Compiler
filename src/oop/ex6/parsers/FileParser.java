package oop.ex6.parsers;

import oop.ex6.ScopeParser;
import oop.ex6.Regex;

import java.util.LinkedList;

public class FileParser {

	private int scope = 0;
	private final LinkedList<String> fixedLines;
	private ScopeParser scopeParser;
	private LinkedList<ScopeParser> innerParsers;
//	private final LinkedList<ScopeParser> methodParsers;

	public FileParser(LinkedList<String> lines) {
		fixedLines = lines;
		scopeParser = GlobalParser.getInstance();
		innerParsers = new LinkedList<>();
//		methodParsers = new LinkedList<>();
	}

	public void run() {
		for (String line : fixedLines) {
			Regex lineRegex = new Regex(line);
			if (lineRegex.enterScope()) { //check for "void"?
				scope++;
				if (scope == 1) {
					scopeParser = new MethodParser(scopeParser);
					scopeParser.getParentParser().addChildParsers(scopeParser);
//					scopeParser.addLine(line);
				}
				if (scope >= 2){
					scopeParser = new IfWhileParser(scopeParser);
					scopeParser.getParentParser().addChildParsers(scopeParser);
//					scopeParser.addLine(line);
				}
				innerParsers.add(scopeParser);
//				continue;
			}
			else if (line.equals("}")) {
				scope--;
				if (scope < 0) {
					return;//ERROR
				}
//				methodParsers.add(scopeParser);
				scopeParser = scopeParser.getParentParser();
				continue;
			}
			if((line = lineRegex.validSuffix()) == null){
				return;//ERROR not valid suffix
			}
			scopeParser.addLine(line);
		}
	}

	public LinkedList<ScopeParser> getInnerParsers(){
		return innerParsers;
	}


}
