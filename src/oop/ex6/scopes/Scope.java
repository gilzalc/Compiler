package oop.ex6.scopes;

import oop.ex6.Keywords;
import oop.ex6.Variable;

import java.util.HashMap;

public abstract class Scope {

	protected HashMap<String, Variable> variables;
	protected Scope outerScope;

	protected Scope(Scope parentScope) {
		variables = new HashMap<>();
		outerScope = parentScope;
	}

	protected Scope() { // global
		variables = new HashMap<>();
		outerScope = null;
	}

	public void addVariable(String varName, Variable var) throws ScopeException {
		// בדיקה שהשם תקין
		if (variables.containsKey(varName)) {
//			return; //Error
			throw new ScopeException("A variable with the same name already exists");
		}
		if (Keywords.getKeywords().contains(varName)){
			throw new ScopeException("The variable name is equal to one of the keywords");
		}
		variables.put(varName, var);
	}

	private Scope getOuterScope(){
		return outerScope;
	}

	public Variable getVariable(String varName) {
		while (outerScope != null) {
			if (variables.containsKey(varName)) {
				return variables.get(varName);
			}
			outerScope = outerScope.getOuterScope();
		}
		return null;
	}
}