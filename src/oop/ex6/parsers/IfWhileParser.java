package oop.ex6.parsers;

import oop.ex6.*;
import oop.ex6.scopes.Global;
import oop.ex6.scopes.IfWhile;
import oop.ex6.scopes.Method;

import java.util.LinkedList;

public class IfWhileParser extends Parser {

	private final static String AND_OR = "(\\|\\|)|(&&)";


	public IfWhileParser(Parser parentParser){
		super(parentParser, new IfWhile(parentParser.getScope()));
	}

	@Override
	public void checkLines() throws IllegalFileFormat, UnmatchingValueError, UnInitializedFinalVar {
		runFirstLine(scopeLines.poll());
		String line;
		String[] methodPars;
		while ((line = scopeLines.poll()) != null){
			if (line.equals("{")){
				runChildParser();
				continue;
			}
			Regex regex = new Regex(line);
			if ((methodPars = regex.checkMethodCall()) != null){
				// בדיקה שהקריאה למתודה תקינה
				String methodName = methodPars[0];
				String paramsString = methodPars[1];
				methodName.split(",")
				Global global = Global.getInstance();
				Method calledMethod = global.getMethod(methodName);
				if (calledMethod == null){
					return;//error not exist method
				}
				calledMethod.getRequiredTypes();
			}
			checkLine(line);
		}
	}

	private boolean checkArgs(LinkedList<>)

	private void runFirstLine(String line){
		Regex regex = new Regex(line);
		String conditions = regex.ifWhileCondition();
		if (conditions == null){
			return;//error not valid if/while
		}
		String[] conditionsArray = conditions.split(AND_OR);
		for (String bool : conditionsArray){
			regex = new Regex(bool);
			bool = regex.startEndSpace();
			if (bool.equals("") || !checkCondition(bool)){
				return;// error not valid condition
			}
		}
	}

	private boolean checkCondition(String cond) {
		if (cond.equals("true") || cond.equals("false")) {
			return true;
		}
		Keywords.Type bool = Keywords.Type.BOOLEAN;
		if (Regex.isVarNameValid(cond)&&!(Keywords.getKeywords().contains(cond))) { //search first
			Variable var = scope.getVariable(cond);
			if (var != null) {
				return bool.isMatching(var.getType());
			}
		}
		return Regex.isValidVal(bool.getRegex(), cond);
	}
}
