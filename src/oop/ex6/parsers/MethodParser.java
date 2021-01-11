package oop.ex6.parsers;

import oop.ex6.IllegalFileFormat;
import oop.ex6.Parser;
import oop.ex6.Regex;
import oop.ex6.Variable;

import java.util.LinkedList;

public class MethodParser extends Parser {

//	LinkedList<>

	public MethodParser(Parser parentParser) {
		super(parentParser, null);
	}

	@Override
	public void checkLines() throws IllegalFileFormat, UnmatchingValueError, UnInitializedFinalVar {
		scopeLines.poll();
		String lastLine = scopeLines.getLast();
		runInnerParsers();
		Regex regex = new Regex(lastLine);
		if (!regex.isReturnLine()){
			return;// error not end with return
		}
	}

//	public void runFirstLine(){
//		for (Variable var: parameters){
//			// מוסיף את הפרמטרים כמשתנים של המתודה
//		}
//	}

	public void addParameters(Variable parameter){

	}

	//	public String getMethodDeclaration() {
//		return scopeLines.getFirst();
//	}
	public boolean isValidArguments(LinkedList<Variable> vars){
		return false;
	}//can be a primitive
}
