package oop.ex6;

import java.util.HashSet;

/**
 * An abstract class that represents the S-java keywords; composed of two enums, one represents the valid
 * Types, and one represents the other keywords. in addition, it has a static method that gets
 */
public abstract class Keywords {
	private static final String BOOLEAN = "boolean";
	private static final String INT = "int";
	private static final String DOUBLE = "double";
	private static final String STRING = "String";
	private static final String CHAR = "char";
	private static final String TRUE = "true";
	private static final String FALSE = "false";
	private static final String VALID_DOUBLE = "-?\\d+(\\.\\d+)?";
	private static final String VALID_BOOL = TRUE + "|" + FALSE + "|" + VALID_DOUBLE;
	private static final String VALID_CHAR = "'.'";
	private static final String VALID_INTEGER_REG = "-?\\d+";
	private static final String VALID_STRING = "\".*\"";
	private static HashSet<String> keyWordsSet;

	/**
	 * enum that represents all of the valid variables types in S-java , with their matching regex expression
	 * for valid value validation
	 */
	public enum Type {
		BOOLEAN(VALID_BOOL, Keywords.BOOLEAN),
		CHAR(VALID_CHAR, Keywords.CHAR),
		DOUBLE(VALID_DOUBLE, Keywords.DOUBLE),
		INT(VALID_INTEGER_REG, Keywords.INT),
		STRING(VALID_STRING, Keywords.STRING);
		//Data members:
		private final String myRegex;
		private final String name;

		Type(String regex, String name) {
			this.myRegex = regex;
			this.name = name;
		}

		public String getRegex() {return this.myRegex;}

		public String getName() {
			return this.name;
		}

		/**
		 * checks if another type is ok for an assignment with a variable of this obj
		 * @param otherType other variable type of the
		 * @return true if ok to assign, false o.w.
		 */
		public boolean isMatching(Type otherType) { //Checks assignments
			if (this == otherType) {
				return true;
			}
			switch (this) {
			case DOUBLE:
				return otherType == INT;
			case BOOLEAN:
				return otherType == INT || otherType == DOUBLE;
			default:
				return false;
			}
		}
	}


	public enum General {
		VOID("void"), FINAL("final"), IF("if"), WHILE("while"), TRUE("true"), FALSE("false"),
		RETURN("return");
		private final String name;

		General(String s) {
			this.name = s;
		}

		private String getName() {
			return this.name;
		}
	}

	/**
	 * returns an Hashset containing all of the keywords in S-java
	 * @return S-java keywords hashSet
	 */
	public static HashSet<String> getKeywords() {
		if (keyWordsSet == null) {
			keyWordsSet = new HashSet<>();

			for (Type t : Type.values()) {
				keyWordsSet.add(t.getName());
			}
			for (General kw : General.values()) {
				keyWordsSet.add(kw.getName());
			}
		}
		return keyWordsSet;
	}
}