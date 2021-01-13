package oop.ex6.blocks;

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
		// checkNameIsOK
		if (variables.containsKey(varName)) {
			throw new ScopeException("A variable with the same name already exists");
		}
		if (Keywords.getKeywords().contains(varName)){
			throw new ScopeException("The variable name is equal to one of the keywords");
		}
		variables.put(varName, var);
	}
	public boolean isShadowingVar(String varName){
		return (this.variables.containsKey(varName));
	}

	private Block getOuterBlock(){
		return outerBlock;
	}

	public Variable getVariable(String varName) {
		Block checkBlock = this;
		do {
			if (checkBlock.variables.containsKey(varName)) {
				return checkBlock.variables.get(varName);
			}
		} while ((checkBlock = checkBlock.getOuterBlock()) != null);
		return null;
	}
}