package oop.ex6;

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

	public void addVariable(String s, Variable var) {
		// בדיקה שהשם תקין
		if (variables.containsKey(s) || Keywords.getKeywords().contains(s)) {
			return; //Error
		}
		variables.put(s, var);
	}

	public Variable getVariable(String varName) {
		while (outerScope != null) {
			if (variables.containsKey(varName)) {
				return variables.get(varName);
			}
			outerScope = outerScope.outerScope;
		}
		return null;
	}
}