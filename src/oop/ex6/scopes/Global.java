package oop.ex6.scopes;


import oop.ex6.scopes.Scope;

import java.util.LinkedList;

public class Global extends Scope {
	private LinkedList<Method> methods;
	public Global(){
		super();
	}

}
