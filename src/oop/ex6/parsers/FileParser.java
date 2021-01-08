package oop.ex6.parsers;

import oop.ex6.ScopeParser;
import oop.ex6.Regex;

import java.util.LinkedList;

public class FileParser {

	private int scope = 0;
	private final LinkedList<String> fixedLines;
	private ScopeParser scopeParser;
	private final LinkedList<ScopeParser> methodParsers;

	public FileParser(LinkedList<String> lines){
		fixedLines = lines;
		scopeParser = new GlobalScopeParser();
		methodParsers = new LinkedList<>();
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
				methodParsers.add(scopeParser);
				scopeParser = scopeParser.getParentParser();
				continue;
			}
			scopeParser.addLine(line);
		}
	}
	public LinkedList<ScopeParser> getMethodParsers(){
		return this.methodParsers;
	}
}
