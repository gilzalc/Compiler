package oop.ex6;

import oop.ex6.parsers.UnInitializedFinalVar;
import oop.ex6.parsers.UnmatchingValueError;

import java.util.LinkedList;

public abstract class Parser {
	private static final String BOOLEAN = "boolean";
	private static final String INT = "int";
	private static final String DOUBLE = "double";
	private static final String STRING = "String";
	private static final String CHAR = "char";
	protected LinkedList<String> scopeLines;
	protected Parser parentParser;
	protected LinkedList<Parser> childParsers;
	protected Scope scope;

	protected Parser(Parser parent, Scope s) {
		parentParser = parent;
		scope = s;
		scopeLines = new LinkedList<>();
		childParsers = new LinkedList<>();
	}

	public abstract void checkLines() throws IllegalFileFormat;

	public Scope getScope() {
		return scope;
	}

	public void addLine(String line) {
		scopeLines.add(line);
	}

	public Parser getParentParser() {
		return parentParser;
	}

	public void addChildParsers(Parser parser) {
		childParsers.add(parser);
	}

	public String pollScopeLines() {
		return scopeLines.poll();
	}

	public LinkedList<Parser> getChildParsers() {
		return childParsers;
	}

	protected void checkLine(String line) {
		Regex reg = new Regex(line);
		reg.setFirstWordsMatcher();
		String firstWord = reg.getFirstWord();
		boolean hasFinal = reg.hasFinal();
		int afterLast = reg.getEndFirst();
		Keywords.Type type = checkVarType(firstWord);
		boolean flag = false;
		if (type != null) {
			reg = new Regex(line.substring(afterLast));
			flag = true;
		} else {
			if (hasFinal) {
				return; //Error - no Type and hasFinal
			}
		}
		String[] varDeclarations;
		try {
			varDeclarations = reg.splitByComma();
			for (String declaration : varDeclarations) {
				reg = new Regex(declaration);
				String[] str = reg.getVarNameAndValue();
				String nameString = str[0];
				String valueString = str[1];
				if (flag) {
					createVars(nameString, valueString, type, hasFinal);
					continue;
				}
				assignVars(nameString, valueString);
			}
		} catch (Exception e) {
			return;
		}
	}

	protected Keywords.Type checkVarType(String firstWord) {
		switch (firstWord) {
		case BOOLEAN:
			return Keywords.Type.BOOLEAN;
		case CHAR:
			return Keywords.Type.CHAR;
		case STRING:
			return Keywords.Type.STRING;
		case INT:
			return Keywords.Type.INT;
		case DOUBLE:
			return Keywords.Type.DOUBLE;
		default:
			return null;
		}
	}

	public void createVars(String nameString, String valueString, Keywords.Type type, boolean hasFinal)
			throws UnInitializedFinalVar, UnmatchingValueError, IllegalFileFormat {
		if (nameString == null || !Regex.isVarNameValid(nameString)) {
			return; //Error - not valid var name
		}
		if (valueString == null) {
			if (hasFinal) {
				throw new UnInitializedFinalVar();
			}
			scope.addVariable(nameString, new Variable(false, false, type));
		} else {
			checkVarValue(valueString, type);
			scope.addVariable(nameString, new Variable(true, hasFinal, type));
		}
	}

	protected void assignVars(String nameString, String valueString) {
		Variable assignedVar = scope.getVariable(nameString);
		if (assignedVar == null) {
			return;//Error - not declared
		}
		if (assignedVar.IsFinal() && assignedVar.isInitialized()) {
			return; //Error - cant assign to final?
		}
		try {
			checkVarValue(valueString, assignedVar.getType());
			assignedVar.initial();
		} catch (UnmatchingValueError error) {
			error.getMessage(); //not good value/var Type
		}
	}


	protected void checkVarValue(String valString, Keywords.Type type) throws UnmatchingValueError {
		Variable var = scope.getVariable(valString);
		if (var != null){
			if (!var.isInitialized()){
				return;// error Assigning uninitialized var
			}
			if (!(type.isMatching(var.getType()))) {
				return; //not compatible Type
			}
		}
		if (!Regex.isValidVal(type.getRegex(), valString)) {
			throw new UnmatchingValueError();
		}
	}
}

