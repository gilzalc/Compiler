package oop.ex6.parsers;

import oop.ex6.*;
import oop.ex6.main.IllegalSFile;
import oop.ex6.regexs.Regex;

public class IfWhileParser extends Parser {

	private final static String AND_OR = "(\\|\\|)|(&&)";

	public IfWhileParser(Parser parentParser) {
		super(parentParser, parentParser.getScope());// לא נכון!!! צריך לתקן
//		super(parentParser, new IfWhile(parentParser.getScope()));
	}

	@Override
	public void checkLines() {
		runFirstLine(scopeLines.poll());
		runInnerParsers();
	}

	private void runFirstLine(String line) {
		Regex regex = new Regex(line);
		String conditions = regex.ifWhileCondition();
		if (conditions == null) {
			return;//error not valid if/while
		}
		String[] conditionsArray = conditions.split(AND_OR);
		for (String bool : conditionsArray) {
			regex = new Regex(bool);
			bool = regex.startEndSpace();
			if (bool.equals("") || !checkCondition(bool)) {
				return;// error not valid condition
			}
		}
	}

	private boolean checkCondition(String cond) {
		if (cond.equals("true") || cond.equals("false")) {
			return true;
		}
		Keywords.Type bool = Keywords.Type.BOOLEAN;
		if (Regex.isVarNameValid(cond) && !(Keywords.getKeywords().contains(cond))) { //search first
			Variable var = scope.getVariable(cond);
			if (var != null) {
				return bool.isMatching(var.getType());
			}
		}
		return Regex.isValidVal(bool.getRegex(), cond);
	}
}
