package oop.ex6.parsers;

import oop.ex6.ScopeParser;

public class MethodParser extends ScopeParser {
	private ScopeParser parentParser;

	public MethodParser(ScopeParser parser){
		super(parser);
	}

}
