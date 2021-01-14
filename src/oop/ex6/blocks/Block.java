package oop.ex6.blocks;

import oop.ex6.Keywords;
import oop.ex6.Variable;

import java.util.HashMap;

/**
 * Represents a general object that is a block of lines of code (aka scope)
 */
public abstract class Block {

	/**
	 * The local variables of the block map, key is it's name String
	 */
	protected HashMap<String, Variable> variables;
	/**
	 * The outer Block of this Block
	 */
	protected Block outerBlock;

	protected Block(Block parentBlock) {
		variables = new HashMap<>();
		outerBlock = parentBlock;
	}

	/**
	 * Default constructor for a block, the global scope uses it
	 */
	protected Block() { // global
		variables = new HashMap<>();
		outerBlock = null;
	}

	/**
	 * Adds a variable to the variables map
	 * @param varName name of the var to add
	 * @param var Variable object to add
	 * @throws ScopeException in case map already has a key with the same name, or the name is part of the
	 * S-Java special keywords
	 */
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

	/**
	 *
	 * checks if a variable(by using its name), is shadowing another variable from an outer scope
	 * @param varName name of variable to check
	 * @return true if it shadows, false o.w
	 */
	public boolean isNewVar(String varName){
		return (!this.variables.containsKey(varName));
	}

	/**
	 * Gets the outer Block of this Block
	 * @return
	 */
	private Block getOuterBlock(){
		return outerBlock;
	}

	/**
	 * Checks if a given String is assigned to a specific variable of the code
	 * @param varName variable's name to check
	 * @return Variable object if found, null o.w
	 */
	public Variable getVariable(String varName) {
		Block checkBlock = this;
		do { //first check in this Block
			if (checkBlock.variables.containsKey(varName)) {
				return checkBlock.variables.get(varName);
			}
		} while ((checkBlock = checkBlock.getOuterBlock()) != null);
		return null;
	}
}