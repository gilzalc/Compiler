package oop.ex6.scopes;

import java.util.HashMap;

public class Global extends Block { //singleton
	private static Global globalScope;
	private final HashMap<String,Method> methodsMap;

	private Global(){
		methodsMap = new HashMap<>();
	}

	public static Global getInstance() {
		if(globalScope == null){
			globalScope = new Global();
		}
		return globalScope;
	}
	public static void setNull(){
		globalScope= null;
	}
	public void addMethod(String methodName,Method method){
//		if (methodsMap.containsKey(scope)) return; //error
		// אנחנו בודקים את זה כבר לפני בglobalParser
		methodsMap.put(methodName, method);
	}


	public Method getMethod(String methodName){
		return methodsMap.get(methodName);
	}
}
