package oop.ex6.parsers;

import oop.ex6.Keywords;
import oop.ex6.regexs.Regex;
import oop.ex6.blocks.*;
import oop.ex6.Variable;

import java.util.LinkedList;

/**
 * An abstract class that represents an object that is responsible for parsing a specific scope
 */
public abstract class Parser {
	private static final String BOOLEAN = "boolean";
	private static final String INT = "int";
	private static final String DOUBLE = "double";
	private static final String STRING = "String";
	private static final String CHAR = "char";
	protected static final int NAME_INDEX = 0;
	private static final int VALUE_INDEX = 1;
	private static final String INVALID_LINE_MSG = "The line is invalid";
	private static final String FINAL_WITH_NO_DECLARATION_MSG = "Missing type for declaration after final";
	private static final String NO_VAR_MSG = "No variable found in line";
	private static final String NOT_DECLARED_MSG = "The variable was never declared";
	private static final String INVALID_DECLARATION_MSG = "invalid declaration";
	private static final String INCOMPATIBLE_TYPE_ASSIGN_MSG = "Incompatible type assignment";
	private static final String INCOMPATIBLE_VAL_ASSIGN_MSG = "The value of the variable does not match " +
															  "the" +
															  " type of variable";
	private static final String INVALID_VAR_NAME = "not valid var name";
	private static final String MODIFYING_FINAL_AND_INIT_MSG = "cant assign a final and initialized " +
															   "variable";
	private static final String ASSIGNING_WITH_UN_INIT_VAR_MSG = "Assigning uninitialized variable";
	private static final String WRONG_NUM_OF_PARAMS_MSG = "Wrong num of parameters";
	private static final String NO_SUCH_METHOD_MSG = "There is no method with such a name";
	private static final String DECLARED_FINAL_BUT_NO_INIT_MSG = "final variable not initialized";
	private static final String BLOCK_ENTER = "{";
	private static final int PARAMETERS_INDEX = 1;

	/**
	 * The lines of the scope - to parse
	 */
	protected LinkedList<String> scopeLines;
	/**
	 * The parent Parser object of this Parser object - that is responsible for parsing it's outer scope
	 */
	protected Parser parentParser;
	/**
	 * A list of all of the direct inner scopes parsers
	 */
	protected LinkedList<Parser> childParsers;
	/**
	 * The specific line Block (scope) that is referenced to this parser
	 */
	protected Block block;

	/**
	 * A constructor for the parser object
	 * @param parent the outer scope parser
	 * @param parserBlock the specific scope
	 */
	protected Parser(Parser parent, Block parserBlock) {
		parentParser = parent;
		block = parserBlock;
		scopeLines = new LinkedList<>();
		childParsers = new LinkedList<>();
	}

	/**
	 * An abstract method that each parser object must implement - the actual parsing and checking of all of
	 * the lines
	 * @throws ParserException in case of illegal behaviour of line
	 */
	public abstract void checkLines() throws ParserException;

	/**
	 * A getter for the specific block object of the parser
	 * @return the block
	 */
	public Block getBlock() {
		return block;
	}

	/**
	 * Adds a line to the scope lines list
	 * @param line String line to add
	 */
	public void addLine(String line) {
		scopeLines.add(line);
	}

	/**
	 * A getter for the outer block parser
	 * @return outer block parser
	 */
	public Parser getParentParser() {
		return parentParser;
	}

	/**
	 * Add a parser to the inner scopes parsers list
	 * @param parser child parser to add
	 */
	public void addChildParsers(Parser parser) {
		childParsers.add(parser);
	}

	/**
	 * A getter method for the child parsers list
	 * @return child parsers list
	 */
	public LinkedList<Parser> getChildParsers() {
		return childParsers;
	}

	/**
	 * Polls a line from the linked list that stores the scope lines
	 * @return the line
	 */
	public String pollBlockLines() {
		return scopeLines.poll();
	}

	/**
	 * This method does the basic checks any scope parser has to do for its lines, it includes checking for a
	 * return line, declaration of variables, or assignment lines.
	 * @param line line of scope to check
	 * @throws ParserException in  case of illegal behaviour of some part of the line
	 */
	protected void checkLine(String line) throws ParserException {
		Regex regex = new Regex(line);
		if (regex.isReturnLine() && (parentParser != null)) { //Make sure it's not return in the global scope
			return;
		}
		if (!regex.setFirstWordsMatcher()) {
			throw new ParserException(INVALID_LINE_MSG);
		}
		String firstWord = regex.getFirstWord();
		boolean hasFinal = regex.hasFinal();
		int afterLast = regex.getEndOfFirst();
		boolean isDeclaring = false;
		Keywords.Type type = checkVarType(firstWord);
		if (type == null) {
			if (hasFinal) {
				throw new UnInitializedException(FINAL_WITH_NO_DECLARATION_MSG);
			}
			Variable var = block.getVariable(firstWord);
			if (var == null) {
				throw new ParserException(NO_VAR_MSG);
			}
		} else {
			isDeclaring = true;
			regex.setCheckLine(line.substring(afterLast)); //remove type string
		}
		manageVarExpressions(regex, isDeclaring, type, hasFinal);
	}

	/**
	 * A method that manages the parsing of a line that has an assignment or declaration of variables
	 * @param regex A regex object of the line
	 * @param isDeclaring boolean value that represent whether the line is a declaration of new vars
	 * @param type the type of the variable
	 * @param hasFinal in case of declaration of a final var
	 * @throws ParserException illegal behaviour of the line
	 */
	private void manageVarExpressions(Regex regex, Boolean isDeclaring, Keywords.Type type, boolean hasFinal)
			throws ParserException {
		String[] varDeclarations = regex.splitDeclarations();
		if (varDeclarations == null) {
			throw new InvalidException(INVALID_DECLARATION_MSG);
		}
		for (String declaration : varDeclarations) {
			regex = new Regex(declaration);
			String[] nameAndValue;
			if ((nameAndValue = regex.getVarNameAndValue()) == null) {
				throw new InvalidException(INVALID_DECLARATION_MSG);
			}
			if (isDeclaring) {
				createVars(nameAndValue[NAME_INDEX], nameAndValue[VALUE_INDEX], type, hasFinal);
				continue;
			}
			assignVars(nameAndValue[NAME_INDEX], nameAndValue[VALUE_INDEX]);
		}
	}


	/**
	 * method that is responsible for creating the variables, and adding them to the scopes variables list
	 * @param nameString String that represents the var name
	 * @param valueString String that represents the value of the string to create
	 * @param type type of var to create
	 * @param hasFinal boolean represents whether the parameter was declared by the final keyword
	 * @throws ParserException in case of invalid name, or final without initializing.
	 */
	public void createVars(String nameString, String valueString, Keywords.Type type, boolean hasFinal)
			throws ParserException {
		if (nameString == null || !Regex.isVarNameValid(nameString)) {
			throw new InvalidException(INVALID_VAR_NAME);
		}
		if (valueString == null) {
			if (hasFinal) {
				throw new UnInitializedException(DECLARED_FINAL_BUT_NO_INIT_MSG);
			}
			block.addVariable(nameString, new Variable(false, false, type));
		} else {
			checkVarValueAssignment(valueString, type);
			block.addVariable(nameString, new Variable(true, hasFinal, type));
		}
	}


	/**
	 * Manages the assignment of the variables, asserting it is made legally, and if needed it initialize
	 * them
	 * and creates a local variable
	 * @param nameString String represents the name of the variable
	 * @param valueString String represents the value of the string, (or a variable name to assign its val)
	 * @throws ParserException in case of Illegal assignment
	 */
	protected void assignVars(String nameString, String valueString) throws ParserException {
		Variable assignedVar = block.getVariable(nameString);
		if (assignedVar == null) {
			throw new UnInitializedException(NOT_DECLARED_MSG);
		}
		if (assignedVar.IsFinal() && assignedVar.isInitialized()) {
			throw new ParserException(MODIFYING_FINAL_AND_INIT_MSG);
		}
		checkVarValueAssignment(valueString, assignedVar.getType());
		if (block.isNewVar(nameString)) { // checks if a local variable has to be created since he shadows
			// another
			block.addVariable(nameString, new Variable(true, assignedVar.IsFinal(), assignedVar.getType()));
		} else {
			assignedVar.initial();
		}
	}


	/**
	 * This method is only responsible for checking if the value assigned is legal, given the type of the
	 * variable to assign
	 * @param valString String that represents the value of the string, or a variable name to assign its
	 * 		val
	 * @param type the s-java type of the variable needed to be assigned with a new val
	 * @throws ParserException parser exception
	 */
	protected void checkVarValueAssignment(String valString, Keywords.Type type) throws ParserException {
		Variable var = block.getVariable(valString);
		if (var != null) {
			if (!var.isInitialized()) {
				throw new UnInitializedException(ASSIGNING_WITH_UN_INIT_VAR_MSG);
			}
			if (!type.isMatching(var.getType())) {
				throw new TypeException(INCOMPATIBLE_TYPE_ASSIGN_MSG);
			}
		} else if (!Regex.isValidVal(type.getRegex(), valString)) {
			throw new InvalidException(INCOMPATIBLE_VAL_ASSIGN_MSG);
		}
	}

	/**
	 * Runs the child parsers abstract method- the check lines that parses its lines
	 * @throws ParserException in case of legal format discovered while parsing
	 */
	protected void runChildParser() throws ParserException {
		Parser childParser = childParsers.poll();
		childParser.checkLines(); // Can't be Null - we call runChildParser method with an open bracket, and
		// each time that the "{" appears, we declared a new child parser.
	}

	/**
	 * Checks for which Type of variable of the s-java language, the String argument is matching
	 * @param firstWord string object to check
	 * @return Type of variable, or null if does not match
	 */
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

	/**
	 * Runs the inner parsers of the parser
	 * @throws ParserException if exception in inner parser is risen
	 */
	protected void runInnerParsers() throws ParserException {
		String line;
		while ((line = scopeLines.poll()) != null) {
			if (line.equals(BLOCK_ENTER)) {
				runChildParser();
				continue;
			}
			if (checkMethodCall(line)) {
				continue;
			}
			checkLine(line);
		}
	}

	/**
	 * This method checks if the given line of the scope is a legal method call
	 * @param line String that is a scope of the line
	 * @return true if it is a valid method call, false o.w
	 * @throws ParserException in case of calling a method that don't exist, or in case of illegal
	 * 		arguments
	 */
	private boolean checkMethodCall(String line) throws ParserException {
		String[] methodPars;
		Regex regex = new Regex(line);
		if ((methodPars = regex.checkMethodCall()) != null) {
			// Check method calling validity
			String methodName = methodPars[NAME_INDEX];
			Global global = Global.getInstance();
			Method calledMethod = global.getMethod(methodName);
			if (calledMethod == null) {
				throw new MethodException(NO_SUCH_METHOD_MSG);
			}
			LinkedList<Keywords.Type> requiredTypes = calledMethod.getRequiredTypes();
			regex = new Regex(methodPars[PARAMETERS_INDEX]);
			if (regex.emptyLine()) {
				if (requiredTypes.size() != 0) {// return true if both has 0 args, false o.w
					throw new MethodException(WRONG_NUM_OF_PARAMS_MSG);
				}
				return true;
			}
			String[] parameters = regex.splitByComma();
			checkArgs(requiredTypes, parameters);
			return true;
		}
		return false;
	}

	/**
	 * A method that given a list of parameters, and another of the required types, checks whether the call
	 * for the method is legal
	 * @param types the list of the ordered required types the method asks for invoking it
	 * @param params list of param to check if they match
	 * @throws ParserException in case of different size of lists, or an incompatible type
	 */
	private void checkArgs(LinkedList<Keywords.Type> types, String[] params) throws ParserException {
		if (types.size() != params.length) {
			throw new MethodException(WRONG_NUM_OF_PARAMS_MSG);
		}
		for (int i = 0; i < params.length; i++) {
			checkVarValueAssignment(params[i], types.get(i));
		}
	}
}

