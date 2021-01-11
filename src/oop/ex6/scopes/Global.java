package oop.ex6.scopes;

import oop.ex6.Scope;

import java.util.HashMap;
import java.util.LinkedList;

public class Global extends Scope { //singleton
	private static Global globalScope;
	private HashMap<String,Method> methodsMap;
	private Global(){

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
	public HashMap<String,Method> getMethodsMap(){
		return methodsMap;
	}
}
