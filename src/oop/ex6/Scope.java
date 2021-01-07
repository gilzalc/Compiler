package oop.ex6;

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
	}
	protected void addVariable(Variable variable){
		variables.add(variable);
	}

//
//	private void aaa(){
//		while ((scope = myScope.getOuter) != null){
//			myScope =scope;
//			if((Variable check = myScope.getVariable(String str)) != null){
//				Variable check =
//			}
//		}
//	}

}
