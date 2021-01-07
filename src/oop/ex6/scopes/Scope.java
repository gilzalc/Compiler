package oop.ex6.scopes;

import oop.ex6.Variable;

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
	protected void addVariable(Variable variable){
		variables.add(variable);
	}

}
