package oop.ex6.scopes;

import oop.ex6.Keywords;
import oop.ex6.Variable;

import java.util.LinkedList;

public class Method extends Scope {

	private final LinkedList<Keywords.Type> requiredTypes;

	public Method(LinkedList<Keywords.Type> types) {
		super(Global.getInstance());
		requiredTypes = types;
	}

	public void addRequiredVar(String varName,Variable variable) throws ScopeException {
		addVariable(varName,variable); //can throw error
		requiredTypes.add(variable.getType());
	}

	public LinkedList<Keywords.Type> getRequiredTypes() {
		return requiredTypes;
	}

}
