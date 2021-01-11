package oop.ex6.scopes;

import oop.ex6.Keywords;
import oop.ex6.Variable;

import java.util.LinkedList;

class Method extends Scope {

	private final LinkedList<Variable> requiredVars;

	Method(LinkedList<Variable> requiredVars) {
		super();
		this.requiredVars = requiredVars;
	}

	public void addRequiredVar(String varName,Variable variable) {
		addVariable(varName,variable); //can throw error
		requiredVars.add(variable);
	}

	public LinkedList<Variable> getRequiredVars() {
		return this.requiredVars;
	}

}
