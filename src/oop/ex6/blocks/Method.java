package oop.ex6.blocks;

import oop.ex6.Keywords;
import oop.ex6.Variable;

import java.util.LinkedList;

/**
 * TheBlock of lines inside a method
 */
public class Method extends Block {
	// A list of required argument types the method asks when is invoked
	private final LinkedList<Keywords.Type> requiredTypes;

	/**
	 * A constructor for the Method object
	 * @param types a list of required argument types the method asks when is invoked
	 */
	public Method(LinkedList<Keywords.Type> types) {
		super(Global.getInstance());
		requiredTypes = types;
	}

	/**
	 * Adds a parameter of the method to the required types of the string, as well for the variables map
	 * @param varName name of the variable
	 * @param variable variable to add
	 * @throws ScopeException in case addVariable throws an error
	 */
	public void addRequiredVar(String varName,Variable variable) throws ScopeException {
		addVariable(varName,variable); //can throw error
		requiredTypes.add(variable.getType());
	}

	/**
	 * A getter method for the required types list
	 * @return a list of required argument types the method asks when is invoked
	 */
	public LinkedList<Keywords.Type> getRequiredTypes() {
		return requiredTypes;
	}

}
