package oop.ex6.parsers;

import oop.ex6.*;
import oop.ex6.regexs.MethodRegex;
import oop.ex6.regexs.Regex;
import oop.ex6.regexs.VariableRegex;
import oop.ex6.blocks.Global;
import oop.ex6.blocks.Method;

/**
 * A class that represents a parser that is responsible for the lines of the global scope
 */
public class GlobalParser extends Parser {
	//-------------------Constants & data members---------------------\\
	private static final int PARAMS_INDEX = 1;
	private static final int ONE_PARAMETER = 1;
	private static final int FIRST_PARAMETER = 0;
	private static final String EMPTY_STRING = "";
	private static GlobalParser globalParser;
	private static final String UNSUPPORTED_METHOD_TYPE_MSG = "method return value has to be void";
	private static final String INVALID_METHOD_DECLARATION_MSG = "Invalid method declaration";
	private static final String INVALID_METHOD_NAME_MSG = "Invalid name for method";
	private static final String DUPLICATE_METHOD_NAME_MSG = "A method with a similar name already exists";
	private static final String WRONG_PARAM_FORMAT_MSG = "wrong parameter format";
	private static final String NOT_VALID_PARAM = "not valid parameter";

	//-------------------Singleton constructor & access---------------------\\
	private GlobalParser() {
		super(null, Global.getInstance());
	}

	/**
	 * gets the singleton object of the global parser
	 * @return global parser unique object
	 */
	public static GlobalParser getInstance() {
		if (globalParser == null) {
			globalParser = new GlobalParser();
		}
		return globalParser;
	}

	// nulls the pointer
	public static void setNull() {
		globalParser = null;
	}
	//-----------------------------Parsing methods----------------------------\\

	@Override
	public void checkLines() throws ParserException {
		for (String line : scopeLines) {
			checkLine(line);
		}
		createMethods();
	}

	/**
	 * This method creates the methods objects of the program
	 * @throws ParserException in case of illegal method declaration line
	 */
	public void createMethods() throws ParserException {
		for (Parser parser : childParsers) {
			String firstLine = parser.pollBlockLines();
			MethodRegex reg = new MethodRegex(firstLine);
			if (!reg.methodStart()) { // removes void and space, if false throw error
				throw new MethodException(UNSUPPORTED_METHOD_TYPE_MSG);
			}
			String[] nameAndParams = reg.getMethodNameParams();
			if (nameAndParams == null) {
				throw new MethodException(INVALID_METHOD_DECLARATION_MSG);
			}
			if (Keywords.getKeywords().contains(nameAndParams[NAME_INDEX])) {
				throw new MethodException(INVALID_METHOD_NAME_MSG);
			}
			if ((Global.getInstance().getMethod(nameAndParams[NAME_INDEX])) != null) {
				throw new MethodException(DUPLICATE_METHOD_NAME_MSG);
			}
			addParameters(nameAndParams, reg, (Method) parser.getBlock());
		}
	}

	/**
	 * Adds the required types and the variables of the method
	 * @param nameAndParams an array composed of the name of the method, and the parameters to add to it
	 * @param reg The method regex object
	 * @param toAdd The method object to add it parameters
	 * @throws ParserException illegal format
	 */
	private void addParameters(String[] nameAndParams, MethodRegex reg, Method toAdd) throws ParserException {
		Global.getInstance().addMethod(nameAndParams[NAME_INDEX], toAdd);
		String parameters = nameAndParams[PARAMS_INDEX];
		reg.setCheckLine(parameters);
		if (reg.emptyLine()) {
			return;
		}
		String[] parametersArr = reg.splitByComma();
		if (parametersArr.length == ONE_PARAMETER && parametersArr[FIRST_PARAMETER].equals(EMPTY_STRING)) {
			return;
		}
		for (String param : parametersArr) {
			VariableRegex paramReg = new VariableRegex(param);
			if (!paramReg.isMatching()) {
				throw new MethodException(WRONG_PARAM_FORMAT_MSG); // wrong format
			}
			Keywords.Type varType = checkVarType(paramReg.getStringType());
			if (varType == null) {
				throw new MethodException(WRONG_PARAM_FORMAT_MSG);// wrong format
			}
			String varName = paramReg.getStringName();
			if (Regex.isVarNameValid(varName)) {
				toAdd.addRequiredVar(varName, (new Variable(true, paramReg.hasFinal(), varType)));
				continue;
			}
			throw new MethodException(NOT_VALID_PARAM);
		}
	}

}
