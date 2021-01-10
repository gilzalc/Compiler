package oop.ex6.parsers;

import oop.ex6.ScopeParser;
import oop.ex6.scopes.IfWhile;

public class IfWhileParser extends ScopeParser {
	private ScopeParser parentParser;

	public IfWhileParser(ScopeParser parser){
		super(parser,new IfWhile());
	}

	@Override
	public void checkLines() {

	}

	public void run() {

	}
}
