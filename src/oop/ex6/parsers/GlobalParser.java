package oop.ex6.parsers;

import oop.ex6.*;
import oop.ex6.regexs.MethodRegex;
import oop.ex6.regexs.Regex;
import oop.ex6.regexs.VariableRegex;
import oop.ex6.blocks.Global;
import oop.ex6.blocks.Method;

public class GlobalParser extends Parser {

	//-------------------Constants & data members---------------------\\
	private static final String COMMA = ",";
	private static GlobalParser globalParser;

	//-------------------Singleton constructor & access---------------------\\
	private GlobalParser() {
		super(null, Global.getInstance());
	}

	public static GlobalParser getInstance() {
		if (globalParser == null) {
			globalParser = new GlobalParser();
		}
		return globalParser;
	}
	public static void setNull(){
		globalParser= null;
	}
	//-----------------------------Parsing methods----------------------------\\
	@Override
	public void checkLines() throws ParserException {
		for (String line : scopeLines) {
			checkLine(line);
//				return; //Error - don't check if method
		}
		createMethods();
	}

	public void createMethods() throws ParserException {
		for (Parser parser : childParsers) {
			String firstLine = parser.pollScopeLines();
			MethodRegex reg = new MethodRegex(firstLine);
			if (!reg.methodStart()) { // removes void and space, if false throw error
//				throw new UnsupportedOperationException("method return value has to be void");
				throw new MethodException("method return value has to be void");
			}
			String methodName = reg.getMethodName();
			if (!Regex.isValidMethodName(methodName)
				&& !(Keywords.getKeywords().contains(methodName))) { // לא הבנתי מה קורה פה...
				throw new MethodException("invalid name for method");
//				return; // invalid name for method
			}
			if ((Global.getInstance().getMethod(methodName)) != null) {
//				throw new DuplicateRequestException("two methods with the same name");
				throw new MethodException("A method with a similar name already exists");
			}
			addParameters(methodName,reg,(Method) parser.getScope());
		}
	}

	private void addParameters(String methodName, MethodRegex reg,Method toAdd) throws ParserException {
		Global.getInstance().addMethod(methodName, toAdd);
		String parameters = reg.getMethodParameters();
		String[] parametersArr = parameters.split(COMMA);// לשנות??
		for (String param : parametersArr) {
			VariableRegex paramReg = new VariableRegex(param);
			if (!paramReg.isMatching()) {
				throw new MethodException("wrong parameter format"); // wrong format
			}
			Keywords.Type varType = checkVarType(paramReg.getStringType());
			if (varType == null) {
				throw new MethodException("wrong parameter format");// wrong format
			}
			String varName = paramReg.getStringName();
			if (Regex.isVarNameValid(varName)) {
				toAdd.addRequiredVar(varName, (new Variable(false, paramReg.hasFinal(), varType)));
				continue;
			}
			throw new MethodException("not valid parameter");
		}
	}

}
