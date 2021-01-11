package oop.ex6.parsers;

import oop.ex6.Regex;
import oop.ex6.Parser;
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
		for (String line : scopeLines){
			if (firstLine){
				firstLine = false;
				runFirstLine(line);
				continue;
			}
			if (line.equals("{")){
				// להיכנס לסקופ פנימי יותר
			}
		}
	}

	private void runFirstLine(String line){
		Regex regex = new Regex(line);
		String condition = regex.ifWhileCondition();
		if (condition == null){
			return;//error not valid if/while
		}
		String[] conditionsArray = condition.split("(\\|\\|)|(&&)");
		for (String bool : conditionsArray){
			if (bool.equals("")||bool.equals((" "))){
				return;// error not valid condition
			}
			//check if bool valid
		}
	}

//	public void run() {
//
//	}
}
