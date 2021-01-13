package oop.ex6.parsers;

import oop.ex6.Keywords;
import oop.ex6.regexs.Regex;
import oop.ex6.blocks.*;
import oop.ex6.Variable;

import java.util.LinkedList;

public abstract class Parser {
	private static final String BOOLEAN = "boolean";
	private static final String INT = "int";
	private static final String DOUBLE = "double";
	private static final String STRING = "String";
	private static final String CHAR = "char";
	protected static final int NAME_INDEX = 0;
	private static final int VALUE_INDEX = 1;
	private static final int PARAMS_INDEX = 1;
	protected LinkedList<String> scopeLines;
	protected Parser parentParser;
	protected LinkedList<Parser> childParsers;
	protected Block block;

	protected Parser(Parser parent, Block parserBlock) {
		parentParser = parent;
		block = parserBlock;
		scopeLines = new LinkedList<>();
		childParsers = new LinkedList<>();
	}

	public abstract void checkLines() throws ParserException;

	public Block getScope() {
		return block;
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

	protected void checkLine(String line) throws ParserException {
		Regex regex = new Regex(line);
		if (regex.isReturnLine()) { //with regex
			return;
		}
		if (!regex.setFirstWordsMatcher()) {
			throw new ParserException("The line is invalid");
		}
		String firstWord = regex.getFirstWord();
		boolean hasFinal = regex.hasFinal();
		int afterLast = regex.getEndOfFirst();
		boolean isCreating = false;
		Keywords.Type type = checkVarType(firstWord);
		if (type == null) {
			if (hasFinal) {
				throw new UnInitializedException("missing type after final");
			}
			Variable var = block.getVariable(firstWord);
			if (var == null) {
				throw new ParserException("No variable found in line");
			}
		} else {
			isCreating = true;
			regex.setCheckLine(line.substring(afterLast));
		}
		manageVarExpressions(regex, isCreating, type, hasFinal);
	}

	private void manageVarExpressions(Regex regex, Boolean isCreating, Keywords.Type type, boolean hasFinal)
			throws ParserException {
		String[] varDeclarations = regex.splitByComma();
		for (String declaration : varDeclarations) {
			regex = new Regex(declaration);
			String[] nameAndValue;
			if ((nameAndValue = regex.getVarNameAndValue()) == null) {
				throw new InvalidException("invalid declaration");
			}
			//			String nameString = nameAndValue[NAME_INDEX];
			//			String valueString = nameAndValue[VALUE_INDEX];
			if (isCreating) {
				createVars(nameAndValue[NAME_INDEX], nameAndValue[VALUE_INDEX], type, hasFinal);
				continue;
			}
			assignVars(nameAndValue[NAME_INDEX], nameAndValue[VALUE_INDEX]);
		}
	}

	public void createVars(String nameString, String valueString, Keywords.Type type, boolean hasFinal)
			throws ParserException {
		if (nameString == null || !Regex.isVarNameValid(nameString)) {
			//			return; //Error - not valid var name
			throw new InvalidException("not valid var name");
		}
		if (valueString == null) {
			if (hasFinal) {
				throw new UnInitializedException("final variable not initialized");
			}
			block.addVariable(nameString, new Variable(false, false, type));
		} else {
			checkVarValueAssignment(valueString, type);
			block.addVariable(nameString, new Variable(true, hasFinal, type));
		}
	}

	protected void assignVars(String nameString, String valueString) throws ParserException {
		Variable assignedVar = block.getVariable(nameString);
		if (assignedVar == null) {
			throw new UnInitializedException("The variable was never declared");
		}
		if (assignedVar.IsFinal() && assignedVar.isInitialized()) {
			throw new ParserException("cant assign a final and initialized variable");
		}
		checkVarValueAssignment(valueString, assignedVar.getType());
		if (block.isNewVar(valueString)) {
			block.addVariable(nameString, new Variable(true, assignedVar.IsFinal(), assignedVar.getType()));
		} else {
			assignedVar.initial();
		}
	}


	protected void checkVarValueAssignment(String valString, Keywords.Type type) throws ParserException {
		Variable var = block.getVariable(valString);
		if (var != null) {
			if (!var.isInitialized()) {
				throw new UnInitializedException("Assigning uninitialized variable");
			}
			if (!type.isMatching(var.getType())) {
				throw new TypeException("Incompatible type");
			}
		} else if (!Regex.isValidVal(type.getRegex(), valString)) {
			throw new InvalidException("The value of the variable does not match the type of variable");
		}
	}

	protected void runChildParser() throws ParserException {
		Parser childParser = childParsers.poll();
		childParser.checkLines(); // Can't be Null - we call runChildParser method with an open bracket, and
		// each time that the "{" appears, we declared a new child parser.
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

	protected void runInnerParsers() throws ParserException {
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
		}
	}

	private boolean checkMethodCall(String line) throws ParserException {
		String[] methodPars;
		Regex regex = new Regex(line);
		if ((methodPars = regex.checkMethodCall()) != null) {
			// Check method calling validity
			String methodName = methodPars[0];

			Global global = Global.getInstance();
			Method calledMethod = global.getMethod(methodName);
			if (calledMethod == null) {
				throw new MethodException("There is no method with this name");
			}
			LinkedList<Keywords.Type> requiredTypes = calledMethod.getRequiredTypes();
			regex = new Regex(methodPars[1]);
			if (regex.emptyLine()) {
				if (requiredTypes.size() != 0) {// return true if both has 0 args, false o.w
					throw new MethodException("Wrong num of parameters");
				}
				return true;
			}
			String[] parameters = regex.splitByComma();
			checkArgs(requiredTypes, parameters);
			return true;
		}
		return false;
	}

	private void checkArgs(LinkedList<Keywords.Type> types, String[] params) throws ParserException {
		if (types.size() != params.length) {
			throw new MethodException("Wrong num of parameters");
		}
		for (int i = 0; i < params.length - 1; i++) {
			checkVarValueAssignment(params[i], types.get(i));
		}
	}
}

