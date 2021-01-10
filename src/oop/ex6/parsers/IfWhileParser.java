package oop.ex6.parsers;

import oop.ex6.Regex;
import oop.ex6.ScopeParser;
import oop.ex6.scopes.IfWhile;
import oop.ex6.scopes.Scope;

public class IfWhileParser extends ScopeParser {
//	private ScopeParser parentParser;
	private boolean firstLine;

	public IfWhileParser(ScopeParser parentParser){
		super(parentParser, new IfWhile());
//		super(parentParser, new IfWhile(parentParser.getScope()));
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
		String[] conBooleans = condition.split("(\\|\\|)|(&&)");
		for (String bool : conBooleans){
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
