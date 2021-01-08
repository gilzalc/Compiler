package oop.ex6;

import java.util.LinkedList;

public abstract class ScopeParser {
	protected LinkedList<String> scopeLines;
	protected ScopeParser parentParser;

	protected ScopeParser(ScopeParser parser){
		parentParser = parser;
		scopeLines = new LinkedList<>();
	}

	public void addLine(String line){
		scopeLines.add(line);
	}

	public ScopeParser getParentParser(){
		return parentParser;
	}

}
