package oop.ex6.parsers;

import oop.ex6.regexs.Regex;
import oop.ex6.Variable;
import oop.ex6.scopes.Method;

import java.util.LinkedList;

public class MethodParser extends Parser {

//	LinkedList<>

	public MethodParser(Parser parser) {
		super(parser, new Method(new LinkedList<>()));
	}

	@Override
	public void checkLines() throws ParserException {
//		scopeLines.poll();
		String lastLine = scopeLines.getLast();
		runInnerParsers();
		Regex regex = new Regex(lastLine);
		if (!regex.isReturnLine()){
			throw new MethodException("Method does not end with return line");
		}
	}

//	public void runFirstLine(){
//		for (Variable var: parameters){
//			// מוסיף את הפרמטרים כמשתנים של המתודה
//		}
//	}

//	public void addParameters(Variable parameter){
//
//	}

	//	public String getMethodDeclaration() {
//		return scopeLines.getFirst();
//	}
//	public boolean isValidArguments(LinkedList<Variable> vars){
//		return false;
//	}//can be a primitive
}
