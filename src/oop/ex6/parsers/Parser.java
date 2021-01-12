package oop.ex6.parsers;

import oop.ex6.Keywords;
import oop.ex6.regexs.Regex;
import oop.ex6.scopes.Scope;
import oop.ex6.Variable;
import oop.ex6.scopes.Global;
import oop.ex6.scopes.Method;
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

	public abstract void checkLines() throws ParserError;

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

	protected void checkLine(String line) throws ParserError {
		Regex reg = new Regex(line);
		if (reg.isReturnLine()) { //with regex
			return;
		}
		reg.setFirstWordsMatcher();
		String firstWord = reg.getFirstWord();
		boolean hasFinal = reg.hasFinal();
		int afterLast = reg.getEndOfFirst();
		boolean isCreating = false;
		Keywords.Type type = checkVarType(firstWord);
		if (type == null) {
			if (hasFinal) {
				throw new UnInitializedError("missing type after final");
			}
			Variable var = scope.getVariable(firstWord);
			if (var == null) {
				throw new ParserError("No variable found in line");
			}
		} else {
			isCreating = true;
			reg = new Regex(line.substring(afterLast));
		}
		manageVarExpressions(reg, isCreating, type, hasFinal);
	}

	private void manageVarExpressions(Regex reg, Boolean isCreating, Keywords.Type type, boolean hasFinal)
			throws ParserError {
		String[] varDeclarations = reg.splitByComma();
		for (String declaration : varDeclarations) {
			reg = new Regex(declaration);
			String[] str = reg.getVarNameAndValue();
			String nameString = str[0];
			String valueString = str[1];
			if (isCreating) {
				createVars(nameString, valueString, type, hasFinal);
				continue;
			}
			assignVars(nameString, valueString);
		}
	}

	public void createVars(String nameString, String valueString, Keywords.Type type, boolean hasFinal)
			throws ParserError {
		if (nameString == null || !Regex.isVarNameValid(nameString)) {
//			return; //Error - not valid var name
			throw new InvalidError("not valid var name");
		}
		if (valueString == null) {
			if (hasFinal) {
				throw new UnInitializedError("final variable not initialized");
			}
			scope.addVariable(nameString, new Variable(false, false, type));
		} else {
			checkVarValueAssignment(valueString, type);
			scope.addVariable(nameString, new Variable(true, hasFinal, type));
		}
	}

	protected void assignVars(String nameString, String valueString) throws ParserError {
		Variable assignedVar = scope.getVariable(nameString);
		if (assignedVar == null) {
//			return;//Error - not declared
			throw new UnInitializedError("The variable was never declared");
		}
		if (assignedVar.IsFinal() && assignedVar.isInitialized()) {
			return; //Error - cant assign to final?
		}
//		try {
		checkVarValueAssignment(valueString, assignedVar.getType());
		assignedVar.initial();
//		} catch (UnmatchingValueError error) {
//			error.getMessage(); //not good value/var Type
//		}
	}


	protected void checkVarValueAssignment(String valString, Keywords.Type type) throws ParserError {
		Variable var = scope.getVariable(valString);
		if (var != null) {
			if (!var.isInitialized()) {
				throw new UnInitializedError("Assigning uninitialized variable");
//				return;// error Assigning uninitialized var
			}
			if (!type.isMatching(var.getType())) {
				throw new TypeError("Incompatible type");
//				return; //not compatible Type
			}
		}
		if (!Regex.isValidVal(type.getRegex(), valString)) {
			throw new InvalidError("The value of the variable does not match the type of variable");
		}
	}

	protected void runChildParser() throws ParserError {
		Parser childParser = childParsers.poll();
//		if ((childParser = childParsers.poll()) == null) {
//			return;//error
//		}
		childParser.checkLines();
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

	protected void runInnerParsers() throws ParserError {
		String line;
		while ((line = scopeLines.poll()) != null) {
			if (line.equals("{")) {
				runChildParser();
				continue;
			}
			if (checkMethodCall(line)) {
				continue;
			}
			checkLine(line);
//			if (!checkLine(line)) {
//				return;//error: not valid line
//			}
		}
	}

	private boolean checkMethodCall(String line) throws ParserError {
		String[] methodPars;
		Regex regex = new Regex(line);
		if ((methodPars = regex.checkMethodCall()) != null) {
			// בדיקה שהקריאה למתודה תקינה
			String methodName = methodPars[0];
			regex = new Regex(methodPars[1]);
			String[] parameters = regex.splitByComma();
			Global global = Global.getInstance();
			Method calledMethod = global.getMethod(methodName);
			if (calledMethod == null) {
//				return false;//error not exist method
				throw new MethodParseError("There is no method by this name");
			}
			LinkedList<Keywords.Type> requiredTypes = calledMethod.getRequiredTypes();
			checkArgs(requiredTypes, parameters);
			return true;
		}
		return false;
	}

	private void checkArgs(LinkedList<Keywords.Type> types, String[] params) throws ParserError {
		if (types.size() != params.length) {
//			return false;//Error Wrong num of params
			throw new MethodParseError("Wrong num of parameters");
		}
		for (int i = 0; i < params.length - 1; i++) {
			checkVarValueAssignment(params[i], types.get(i));
		}
//		return true;
	}
}

