package oop.ex6.parsers;

import oop.ex6.ScopeParser;
import oop.ex6.Variable;

import java.util.LinkedList;

public class MethodParser extends ScopeParser {
	private ScopeParser parentParser;

	public MethodParser(ScopeParser parser) {
		super(parser);
	}

	public String getMethodDeclaration() {
		return scopeLines.getFirst();
	}
	public boolean isValidArguments(LinkedList<Variable> vars){
		return false;
	}//can be a primitive
}
