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

	public ScopeParser getParentParser(){
		return parentParser;
	}

	public void addChildParsers(ScopeParser parser){
		childParsers.add(parser);
	}

	public LinkedList<String> getScopeLines(){
		return scopeLines;
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

	public void createVars(boolean hasFinal, Keywords.Type type, Regex regex)
			throws UnInitializedFinalVar, UnmatchingValueError, IllegalFileFormat {
		String[] varDeclarations = regex.splitByComma();
		for (String declaration : varDeclarations) {
			Regex reg = new Regex(declaration);
			String[] str = regex.getVarNameAndValue();
			String nameString = str[0];
			String valueString = str[1];
			if (nameString == null || !Regex.isVarNameValid(nameString)) {
				return; //Error - not valid var name
			}
//			if (!Regex.isVarNameValid(nameString)) { // wo space in the end
//				return; //Error
//			}
			if (valueString == null) {
				if (hasFinal) {
					throw new UnInitializedFinalVar();
				}
				scope.addVariable (nameString, new Variable(false, false, type));
			} else {
				checkVarValue(valueString, type);
				scope.addVariable(nameString, new Variable(true, hasFinal, type));
			}
		}
	}

	protected void checkVarValue(String valString, Keywords.Type type) throws UnmatchingValueError {
		if (Regex.isValidVal(type.getRegex(), valString)) {
			return;
		}
		throw new UnmatchingValueError();
	}

	protected void AssignVars(Regex reg) {

	}
}
