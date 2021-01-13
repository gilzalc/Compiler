package oop.ex6.scopes;

import oop.ex6.Keywords;
import oop.ex6.Variable;

import java.util.HashMap;

public abstract class Block {

	protected HashMap<String, Variable> variables;
	protected Block outerBlock;

	protected Block(Block parentBlock) {
		variables = new HashMap<>();
		outerBlock = parentBlock;
	}

	protected Block() { // global
		variables = new HashMap<>();
		outerBlock = null;
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

	private Block getOuterScope(){
		return outerBlock;
	}

	public Variable getVariable(String varName) {
		while (outerBlock != null) {
			if (variables.containsKey(varName)) {
				return variables.get(varName);
			}
			outerBlock = outerBlock.getOuterScope();
		}
		return null;
	}
}