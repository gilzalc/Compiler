package oop.ex6;

import oop.ex6.parsers.MethodParser;

import java.util.LinkedList;

public abstract class ScopeParser {
	protected LinkedList<String> scopeLines;
	protected ScopeParser parentParser;
	protected LinkedList<ScopeParser> childParsers;

	protected ScopeParser(ScopeParser parser){
		parentParser = parser;
		scopeLines = new LinkedList<>();
		childParsers = new LinkedList<>();
	}
	public abstract void checkLines() throws IllegalFileFormat;

	public void addLine(String line){
		scopeLines.add(line);
	}

	public ScopeParser getParentParser(){
		return parentParser;
	}

	public void addChildParsers(ScopeParser parser){
		childParsers.add(parser);
	}

	public LinkedList<String> getScopeLines(){
		return scopeLines;
	}
}
