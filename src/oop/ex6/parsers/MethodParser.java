package oop.ex6.parsers;

import oop.ex6.Parser;

public class MethodParser extends Parser {
	private Parser parentParser;

	public MethodParser(Parser parser){
		super(parser);
	}

}
