package oop.ex6.parsers;

import oop.ex6.ScopeParser;
import oop.ex6.Regex;
import oop.ex6.Keywords;

import java.util.LinkedList;

public class GlobalScopeParser extends ScopeParser {
	private final LinkedList<String> assignmentsLines = new LinkedList<>();
	protected GlobalScopeParser() {
		super(null);
	}

	protected void checkLines() throws UnInitializedFinalVar {
		for (String line : scopeLines) {
			int firstSpace = line.indexOf(" ");
			String firstWord = line.substring(0,firstSpace);
			if (firstWord.equals("final")){
				line = line.substring(firstSpace);
				checkFinalVars(line);
			}
			checkVarType(firstWord);
			Regex lineReg = new Regex(line);

		}
	}

	private Keywords.Type checkVarType(String firstWord) {
		switch (firstWord) {
		case "boolean":
			return Keywords.Type.BOOLEAN;
		case "char":
			return Keywords.Type.CHAR;
		case "String":
			return Keywords.Type.STRING;
		case "int":
			return Keywords.Type.INT;
		case "double":
			return Keywords.Type.DOUBLE;

		default:
			throw new IllegalStateException("Unexpected value: " + firstWord);
		}
	}

	void checkVars() {

	}

	void checkFinalVars(String line) throws UnInitializedFinalVar {
		throw new UnInitializedFinalVar();
	}
}
