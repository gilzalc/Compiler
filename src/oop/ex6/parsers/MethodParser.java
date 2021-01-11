package oop.ex6.parsers;

import oop.ex6.IllegalFileFormat;
import oop.ex6.Parser;
import oop.ex6.Variable;

import java.util.LinkedList;

public class MethodParser extends Parser {

	public MethodParser(Parser parser) {
		super(parser, parser.getScope());// הפרמטר השני לא נכון!!!
	}

	@Override
	public void checkLines() throws IllegalFileFormat {
	}

	//	public String getMethodDeclaration() {
//		return scopeLines.getFirst();
//	}
	public boolean isValidArguments(LinkedList<Variable> vars){
		return false;
	}//can be a primitive
}
