package oop.ex6.scopes;

import oop.ex6.Variable;

import java.util.HashMap;

public abstract class Scope {

	protected HashMap<String, Variable> variables;

	protected Scope outerScope;

	protected Scope(Scope scope){
		variables = new HashMap<>();
		outerScope = scope;
	}

	protected Scope(){ // global
		variables = new HashMap<>();
		outerScope = null;
	}

	protected void addVariable(Variable variable){
		variables.put(variable.getName(), (variable));
	}

}