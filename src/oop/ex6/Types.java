package oop.ex6;

enum Type {

	BOOLEAN("boolean", "D"),
	CHAR("char", "D"), DOUBLE("double", "d"), INT("int", "a"),
	STRING("String", "d");
	private final String myName;

	Type(String s, String regex) {
		this.myName = s;
	}

	String getName() {
		return this.myName;
	}
}

enum Keywords {
	VOID("void"), FINAL("final"), IF("if"), WHILE("while"), TRUE("true"), FALSE("false"),
	RETURN("return");
	private final String myName;

	Keywords(String s) {
		this.myName = s;
	}

	String getName() {
		return this.myName;
	}

}