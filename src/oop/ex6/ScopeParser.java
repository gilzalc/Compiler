package oop.ex6;

import oop.ex6.parsers.MethodParser;
import oop.ex6.parsers.UnInitializedFinalVar;
import oop.ex6.parsers.UnmatchingValueError;
import oop.ex6.scopes.Scope;

import java.util.LinkedList;

public abstract class ScopeParser {

	private static final String BOOLEAN = "boolean";
	private static final String INT = "int";
	private static final String DOUBLE = "double";
	private static final String STRING = "String";
	private static final String CHAR = "char";
	protected LinkedList<String> scopeLines;
	protected ScopeParser parentParser;
	protected LinkedList<ScopeParser> childParsers;
	protected Scope scope;

	protected ScopeParser(ScopeParser parent, Scope s){
		parentParser = parent;
		scope = s;
		scopeLines = new LinkedList<>();
		childParsers = new LinkedList<>();

	}



	public abstract void checkLines() throws IllegalFileFormat;

	public Scope getScope(){
		return scope;
	}

	public void addLine(String line){
		scopeLines.add(line);
	}

	public ScopeParser getParentParser() {
		return parentParser;
	}

	public void addChildParsers(ScopeParser parser) {
		childParsers.add(parser);
	}

	public LinkedList<String> getScopeLines() {
		return scopeLines;
	}

	private void checkLine(String line) throws IllegalFileFormat, UnInitializedFinalVar,
											   UnmatchingValueError {
		Regex reg = new Regex(line);
		//		Matcher matcher = reg.getFirstWords();
		//		matcher.find();
		//		String firstWord = matcher.group(FIRST);
		//		boolean hasFinal = matcher.group(FINAL) != null;
		reg.setFirstWordsMatcher();
		String firstWord = reg.getFirstWord(FIRST);
		boolean hasFinal = (reg.getFinalGroup(FINAL) != null);
		int afterLast = reg.getEndFirst(FIRST);
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
		//			createVars(hasFinal, type, reg);
		String[] varDeclarations = reg.splitByComma();
		for (String declaration : varDeclarations) {
			reg = new Regex(declaration);
			String[] str = reg.getVarNameAndValue();
			String nameString = str[0];
			String valueString = str[1];
			if (flag) {
				createVars(nameString, valueString, type, hasFinal);
				continue;
			}
			assignVars(nameString,
					   valueString);
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
		try {
			checkVarValue(valueString, assignedVar.getType());
		} catch (UnmatchingValueError error) {
			error.getMessage(); //not good value/var Type
		}
	}


	protected void checkVarValue(String valString, Keywords.Type type) throws UnmatchingValueError {
		Variable var = scope.getVariable(valString);
		if (var != null && !(type.isMatching(var.getType()))) {
			return; //not compatible Type
		}
		if (!Regex.isValidVal(type.getRegex(), valString)) {
			throw new UnmatchingValueError();
		}
	}
//	protected Variable findVariable(){
//		while (scope.)
	}

