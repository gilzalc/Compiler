package oop.ex6;

import java.util.LinkedList;

public abstract class Parser {
	protected LinkedList<String> scopeLines;
	protected Parser parentParser;

	protected Parser(Parser parser){
		parentParser = parser;
		scopeLines = new LinkedList<>();
	}

	public void addLine(String line){
		scopeLines.add(line);
	}

	public Parser getParentParser(){
		return parentParser;
	}

}
