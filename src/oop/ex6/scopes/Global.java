package oop.ex6.scopes;


import oop.ex6.scopes.Scope;

import java.util.HashMap;
import java.util.LinkedList;

public class Global extends Scope { //singleton
	private static Global globalScope;
	private HashMap<String,Method> methods;
	private Global(){

	}

	public static Global getInstance() {
		if(globalScope == null){
			globalScope = new Global();
		}
		return globalScope;
	}

	public void addMethod(String s,Method m){
		if (methods.containsKey(s)) return;//error
		methods.put(s,m);
	}
}
