package oop.ex6.parsers;

import oop.ex6.ScopeParser;
import oop.ex6.scopes.IfWhile;
import oop.ex6.scopes.Scope;

public class IfWhileParser extends ScopeParser {
//	private ScopeParser parentParser;

	public IfWhileParser(ScopeParser parentParser){
		super(parentParser, new IfWhile(parentParser.getScope()));
	}

	@Override
	public void checkLines() {

	}

	public void run() {

	}
}
