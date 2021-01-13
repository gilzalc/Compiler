package oop.ex6.parsers;

import oop.ex6.regexs.Regex;
import oop.ex6.blocks.Method;

import java.util.LinkedList;

/**
 * A class that parses the linas of a method
 */
public class MethodParser extends Parser {


	/**
	 * constructor for the Method parser
	 * @param parser parent (global) parser
	 */
	public MethodParser(Parser parser) {
		super(parser, new Method(new LinkedList<>()));
	}


	@Override
	public void checkLines() throws ParserException {
		String lastLine = scopeLines.getLast();
		runInnerParsers();
		Regex regex = new Regex(lastLine);
		if (!regex.isReturnLine()){
			throw new MethodException("Method does not end with return line");
		}
	}

}
