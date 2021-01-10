package oop.ex6.scopes;

import oop.ex6.Keywords;
import oop.ex6.Regex;
import oop.ex6.Variable;

public class IfWhile extends Scope {
	private boolean checkCondition(String cond) {
		if (cond.equals("true") || cond.equals("false")) {
			return true;
		}
		Keywords.Type bool = Keywords.Type.BOOLEAN;
		if (Regex.isVarNameValid(cond)) { //search first
			Variable var = getVariable(cond);
			if (var != null) {
				return bool.isMatching(var.getType());
			}
		}
		return Regex.isValidVal(bool.getRegex(), cond);


	}
}
