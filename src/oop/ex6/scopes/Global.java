package oop.ex6.scopes;

import java.util.HashMap;

public class Global extends Scope { //singleton
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

	public void addMethod(String s,Method m){
		if (methodsMap.containsKey(s)) return; //error
		methodsMap.put(s,m);
	}


	public Method getMethod(String methodName){
		return methodsMap.get(methodName);
	}
}
