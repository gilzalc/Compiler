package oop.ex6.parsers;

import oop.ex6.*;
import oop.ex6.regexs.Regex;
import oop.ex6.blocks.Block;

/**
 * Parser of an If or a While loop scope lines
 */
public class IfWhileParser extends Parser {

	public IfWhileParser(Parser parentParser, Block ifWhileBlock) {
		super(parentParser, ifWhileBlock);
	}

	@Override
	public void checkLines() throws ParserException {
		runFirstLine(scopeLines.poll());
		runInnerParsers();
	}

	/**
	 * Runs the first line of the if/while scope
	 * @param line String that represents the first scope line
	 * @throws ParserException in case illegal if/while scope
	 */
	private void runFirstLine(String line) throws ParserException {
		Regex regex = new Regex(line);
		String conditions = regex.ifWhileCondition();
		if (conditions == null) {
			throw new IfWhileException("not valid if/while first line");
		}
		regex = new Regex(conditions);
		String[] conditionsArray = regex.splitCondition();
		for (String bool : conditionsArray) {
//			regex = new Regex(bool);
//			bool = regex.startEndSpace();
//			if (bool.equals("") || !checkCondition(bool)) {
			if (!checkCondition(bool)) {
				throw new IfWhileException("not valid condition");
			}
		}
	}

	/**
	 * checks whether a given string represents a valid boolean value, respecting the exercise descriptions
	 * @param cond String that represents the boolean (supposedly) object to check
	 * @return true if condition is ok, false o.w
	 */
	private boolean checkCondition(String cond) {
		if (cond.equals("true") || cond.equals("false")) {
			return true;
		}
		Keywords.Type bool = Keywords.Type.BOOLEAN;
		if (Regex.isVarNameValid(cond) && !(Keywords.getKeywords().contains(cond))) { //search first
			Variable var = block.getVariable(cond);
			if (var != null) {
				return (bool.isMatching(var.getType()) && var.isInitialized());
			}
		}
		return Regex.isValidVal(bool.getRegex(), cond);
	}
}
