package oop.ex6.parsers;

import oop.ex6.*;
import oop.ex6.regexs.Regex;
import oop.ex6.scopes.Block;

public class IfWhileParser extends Parser {

	public IfWhileParser(Parser parentParser, Block ifWhileBlock) {
		super(parentParser, ifWhileBlock);
//		super(parentParser, new IfWhile(parentParser.getScope()));
	}

	@Override
	public void checkLines() throws ParserException {
		runFirstLine(scopeLines.poll());
		runInnerParsers();
	}

	private void runFirstLine(String line) throws ParserException {
		Regex regex = new Regex(line);
		String conditions = regex.ifWhileCondition();
		if (conditions == null) {
//			return;//error not valid if/while
			throw new IfWhileException("not valid if/while first line");
		}
		regex = new Regex(conditions);
		String[] conditionsArray = regex.splitCondition();
		for (String bool : conditionsArray) {
//			regex = new Regex(bool);
//			bool = regex.startEndSpace();
//			if (bool.equals("") || !checkCondition(bool)) {
			if (!checkCondition(bool)) {
//				return;// error not valid condition
				throw new IfWhileException("not valid condition");
			}
		}
	}

	private boolean checkCondition(String cond) {
		if (cond.equals("true") || cond.equals("false")) {
			return true;
		}
		Keywords.Type bool = Keywords.Type.BOOLEAN;
		if (Regex.isVarNameValid(cond) && !(Keywords.getKeywords().contains(cond))) { //search first
			Variable var = block.getVariable(cond);
			if (var != null) {
				return bool.isMatching(var.getType());
			}
		}
		return Regex.isValidVal(bool.getRegex(), cond);
	}
}
