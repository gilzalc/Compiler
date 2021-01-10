package oop.ex6.parsers;

import oop.ex6.*;
import oop.ex6.scopes.Global;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;

public class GlobalScopeParser extends ScopeParser {
	//-------------------Constants & data members---------------------\\

	private static final String FINAL = "final";
	private static final String FIRST = "first";
	private static final String BOOLEAN = "boolean";
	private static final String INT = "int";
	private static final String DOUBLE = "double";
	private static final String STRING = "String";
	private static final String CHAR = "char";
	private final Global globalScope;
	private static GlobalScopeParser globalScopeParser;

	//-------------------Singleton constructor & access---------------------\\
	private GlobalScopeParser() {
		super(null);
		globalScope = Global.getInstance();
		//		Methods = new HashMap<>();
	}

	public static GlobalScopeParser getInstance() {
		if (globalScopeParser == null) {
			globalScopeParser = new GlobalScopeParser();
		}
		return globalScopeParser;
	}

	//-----------------------------Parsing methods----------------------------\\
	public void checkLines() throws IllegalFileFormat {
		for (String line : scopeLines) {
			checkLine(line);
		}
	}

	private void checkLine(String line) throws IllegalFileFormat {
		Regex reg = new Regex(line);
		Matcher matcher = reg.getFirstWords();
		matcher.find();
		String firstWord = matcher.group(FIRST);
		boolean hasFinal = matcher.group(FINAL) != null;
		Keywords.Type type = checkVarType(firstWord);
		if (type == null) {
			if (hasFinal) {
				return; //Error - no Type and hasFinal
			}
			AssignVars(reg);
		}
		reg = new Regex(line.substring(matcher.end(FIRST)));
		try {
			createVars(hasFinal, type, reg);
		} catch (UnInitializedFinalVar | UnmatchingValueError unInitializedFinalVar) {
			unInitializedFinalVar.printStackTrace();
		}
	}

	private void AssignVars(Regex reg) {
	}


	private void checkVarValue(String valString, Keywords.Type type) throws UnmatchingValueError {
		if (Regex.isValidVal(type.getRegex(), valString)) {
			return;
		}
		throw new UnmatchingValueError();
	}

	private Keywords.Type checkVarType(String firstWord) {
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
			String[] str = Regex.getVarNameAndValue(declaration);
			String nameString = str[1], valueString = str[2];
			if (nameString == null) {
				return; //Error - no var name
			}
			if (!Regex.isVarNameValid(nameString)) { // wo space in the end
				return; //Error
			}
			if (valueString == null) {
				if (hasFinal) {
					throw new UnInitializedFinalVar();
				}

				globalScope.addVariable
						(nameString, new Variable(false, false, type));
			} else {
				checkVarValue(valueString, type);
				globalScope.addVariable(nameString, new Variable(true, hasFinal, type));
			}
		}


		//	private static class GlobalScopeVarFactory{
		//
		//	}
	}

	public void createMethods() {
		for (ScopeParser parser : childParsers) {
			String firstLine = parser.getScopeLines().getFirst();

			// Create methods
			//			Method m = new Method();
			//			globalScope.addMethod(methodName,m);
		}
	}
}
