package oop.ex6.parsers;

import oop.ex6.*;
import oop.ex6.scopes.Global;
import oop.ex6.scopes.Method;

import java.util.LinkedList;

public class GlobalParser extends Parser {
	//-------------------Constants & data members---------------------\\

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
	public void checkLines() throws IllegalFileFormat {
		for (String line : scopeLines) {
			return;
			//			checkLine(line);
		}
	}


	public void createMethods() {
		for (Parser parser : childParsers) {
			String firstLine = parser.getScopeLines().getFirst();
			Regex reg = new Regex(firstLine);
			reg.methodStart();// removes void and space, if false throw error
			String methodName = reg.getMethodName();
			if (!Regex.isValidMethodName(methodName)) {
				return; // invalid name for method
			}
			if ((Global.getInstance().getMethodsMap().containsKey(methodName))) {
				return; // - method name already exists;
			}
			Method toAdd = new Method(new LinkedList<Variable>());
			Global.getInstance().addMethod(methodName, toAdd);
			String parameters = reg.getMethodParameters();
			if (parameters.endsWith(",")) { //edge case
				return; //Error
			}
			String[] parametersArr = parameters.split(",");
			for (String param : parametersArr) {
				VariableRegex paramReg = new VariableRegex(param);
				if (!paramReg.isMatching()) {
					return;//Error
				}
				Keywords.Type varType = checkVarType(paramReg.getStringType());
				if (varType == null) {
					return; //Error
				}
				String varName = paramReg.getStringName();
				if (Regex.isVarNameValid(varName)) {
					toAdd.addRequiredVar(varName, (new Variable(false, paramReg.hasFinal(), varType)));
					continue;
				}
				return;// error
			}

			// Create methods - regex for req arguments
			//			Method m = new Method();
			//			globalScope.addMethod(methodName,m);
		}
	}

	private void checkParam(String param) {

	}

}
