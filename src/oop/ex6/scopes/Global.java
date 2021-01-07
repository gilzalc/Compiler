package oop.ex6.scopes;


import java.util.LinkedList;

class Global extends Scope {
	private LinkedList<Method> methods;
	public Global(){
		super();
	}
}
