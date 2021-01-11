package oop.ex6.parsers;

import com.sun.jdi.request.DuplicateRequestException;
import oop.ex6.*;
import oop.ex6.regexs.MethodRegex;
import oop.ex6.regexs.VariableRegex;
import oop.ex6.scopes.Global;
import oop.ex6.scopes.Method;

import java.util.LinkedList;

public class GlobalParser extends Parser {
	//-------------------Constants & data members---------------------\\
	private static final String COMMA = ",";
	private static GlobalParser globalParser;

	//-------------------Singleton constructor & access---------------------\\
	private GlobalParser() {
		//		globalScope = Global.getInstance();
		super(null, Global.getInstance());
		//		Methods = new HashMap<>();
	}

	public static GlobalParser getInstance() {
		if (globalParser == null) {
			globalParser = new GlobalParser();
		}
		return globalParser;
	}

	//-----------------------------Parsing methods----------------------------\\
	@Override
	public void checkLines() {
		for (String line : scopeLines) {
			try {
				if (!checkLine(line)){
					return; //Error - don't check if method
				}
				createMethods();
			} catch (Exception exception) {
				System.err.println(exception.getMessage());
			}
		}
	}


	public void createMethods() throws Exception {
		for (Parser parser : childParsers) {
			String firstLine = parser.pollScopeLines();
			MethodRegex reg = new MethodRegex(firstLine);
			if (!reg.methodStart()) { // removes void and space, if false throw error
				throw new UnsupportedOperationException("method return value has to be void");
			}
			String methodName = reg.getMethodName();
			if (!Regex.isValidMethodName(methodName)
				&& !(Keywords.getKeywords().contains(methodName))) {
				return; // invalid name for method
			}
			if (Global.getInstance().getMethodsMap().containsKey(methodName)) {
				throw new DuplicateRequestException("two methods with the same name");
			}
			addParameters(methodName, reg);
		}

	}

	private void addParameters(String methodName, MethodRegex reg) throws Exception {
		Method toAdd = new Method(new LinkedList<>());
		Global.getInstance().addMethod(methodName, toAdd);
		String parameters = reg.getMethodParameters();
		String[] parametersArr = parameters.split(COMMA);
		for (String param : parametersArr) {
			VariableRegex paramReg = new VariableRegex(param);
			if (!paramReg.isMatching()) {
				throw new Exception(); // wrong format
			}
			Keywords.Type varType = checkVarType(paramReg.getStringType());
			if (varType == null) {
				throw new Exception();// wrong format
			}
			String varName = paramReg.getStringName();
			if (Regex.isVarNameValid(varName)) {
				toAdd.addRequiredVar(varName, (new Variable(false, paramReg.hasFinal(), varType)));
				continue;
			}
			return;// error
		}
	}

//	private void checkParam(String param) {
//
//	}

}
