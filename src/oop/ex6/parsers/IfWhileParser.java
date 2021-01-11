package oop.ex6.parsers;

import oop.ex6.Keywords;
import oop.ex6.Regex;
import oop.ex6.Parser;
import oop.ex6.Variable;
import oop.ex6.scopes.IfWhile;
public class IfWhileParser extends Parser {
//	private ScopeParser parentParser;
	private boolean firstLine;

	public IfWhileParser(Parser parentParser){
		super(parentParser, new IfWhile(parentParser.getScope()));
		firstLine = true;

	}

	@Override
	public void checkLines() {
		runFirstLine(scopeLines.poll());
		String line;
		while ((line = scopeLines.poll()) != null){
			if (line.equals("{")){
				runChildParser();
				continue;
			}
			Regex regex = new Regex(line);
			if (regex.checkMethodCall()){
				// בדיקה שהקריאה למתודה תקינה
			}
			checkLine(line);
		}
	}

	private void runFirstLine(String line){
		firstLine = false;
		Regex regex = new Regex(line);
		String condition = regex.ifWhileCondition();
		if (condition == null){
			return;//error not valid if/while
		}
		String[] conditionsArray = condition.split("(\\|\\|)|(&&)");
		for (String bool : conditionsArray){
			bool = bool.replaceAll(" ", "");
			if (bool.equals("")){
				return;// error not valid condition
			}
			if(!checkCondition(bool)){
				return;//Error not valid condition
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

	private void runChildParser(){
		Parser childParser;
		if ((childParser = childParsers.poll()) == null){
			return;//error
		}
		childParser.checkLines();
	}
}
