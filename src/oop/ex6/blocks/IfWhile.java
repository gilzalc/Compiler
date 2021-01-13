package oop.ex6.blocks;

public class IfWhile extends Block {

	public IfWhile(Block parentBlock){
		super(parentBlock);
	}

//	private boolean checkCondition(String cond) {
//		if (cond.equals("true") || cond.equals("false")) {
//			return true;
//		}
//		Keywords.Type bool = Keywords.Type.BOOLEAN;
//		if (Regex.isVarNameValid(cond)&&!(Keywords.getKeywords().contains(cond))) { //search first
//			Variable var = getVariable(cond);
//			if (var != null) {
//				return bool.isMatching(var.getType());
//			}
//		}
//		return Regex.isValidVal(bool.getRegex(), cond);
//	}
}
