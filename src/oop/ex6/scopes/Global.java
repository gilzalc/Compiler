package oop.ex6.scopes;


import oop.ex6.Scope;

import java.util.LinkedList;

class Global extends Scope {
	private LinkedList<Method> methods;
	public Global(){
		super();
	}
	Scope s = new IfWhile();

}
