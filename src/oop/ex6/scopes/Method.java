package oop.ex6.scopes;

import oop.ex6.Keywords;
import oop.ex6.scopes.Scope;
import oop.ex6.Variable;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.LinkedList;

class Method extends Scope {
	private final LinkedList<Keywords.Type> requiredTypes;

	Method(LinkedList<Keywords.Type> requiredTypes) {
		super();
		this.requiredTypes = requiredTypes;
	}

	public void addRequiredType(Keywords.Type type) {
		requiredTypes.add(type);
	}

	public LinkedList<Keywords.Type> getRequiredTypes() {
		return this.requiredTypes;
	}

}
