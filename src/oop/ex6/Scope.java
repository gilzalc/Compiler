package oop.ex6;

import java.util.HashSet;

public abstract class Scope {

	protected HashSet<Variable> variables;
	protected Scope outerScope;


	protected Scope(Scope scope){
		variables = new HashSet<>();
		outerScope = scope;
	}

	protected Scope(){ // global
		variables = new HashSet<>();
		outerScope = null;
	}
}
