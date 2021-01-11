package oop.ex6.scopes;

import oop.ex6.*;

public class IfWhile extends Scope {

	public IfWhile(Scope parentScope){
		super(parentScope);
	}

	private boolean checkCondition(String cond) {
		if (cond.equals("true") || cond.equals("false")) {
			return true;
		}
		Keywords.Type bool = Keywords.Type.BOOLEAN;
		if (Regex.isVarNameValid(cond)&&!(Keywords.getKeywords().contains(cond))) { //search first
			Variable var = getVariable(cond);
			if (var != null) {
				return bool.isMatching(var.getType());
			}
		}
		return Regex.isValidVal(bool.getRegex(), cond);


	}
}
