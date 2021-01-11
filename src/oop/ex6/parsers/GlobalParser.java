package oop.ex6.parsers;

import oop.ex6.*;
import oop.ex6.scopes.Global;

import java.util.regex.Matcher;

public class GlobalParser extends ScopeParser {
	//-------------------Constants & data members---------------------\\

//	private static final String FINAL = "final";
//	private static final String FIRST = "first";
//	private static final String BOOLEAN = "boolean";
//	private static final String INT = "int";
//	private static final String DOUBLE = "double";
//	private static final String STRING = "String";
//	private static final String CHAR = "char";
//	private final Global globalScope;
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
		for (ScopeParser parser : childParsers) {
			String firstLine = parser.getScopeLines().getFirst();
			Regex reg = new Regex(firstLine);
			reg.methodStart();
			String methodName = reg.getNextWord();
			if (!Regex.isValidMethodName(methodName))
				return; // invalid name for method
			if ((Global.getInstance().getMethodsMap().containsKey(methodName))){
				return; // - duplicate method name;
			}
			// Create methods - regex for req arguments
			//			Method m = new Method();
			//			globalScope.addMethod(methodName,m);
		}
	}

}
