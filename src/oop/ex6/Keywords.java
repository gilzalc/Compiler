package oop.ex6;

import java.util.HashSet;

/**
 * An abstract class that represents the S-java keywords; composed by two enums, one represents the valid
 * Types, and one represents the other keywords. in addition, it has a static method that gets
 */
public abstract class Keywords {
	private static final String TRUE = "true";
	private static final String FALSE = "false";
	private static final String VALID_DOUBLE = "-?\\d+(\\.\\d+)?";
	private static final String VALID_BOOL = TRUE + "|" + FALSE + "|" + VALID_DOUBLE;
	private static final String VALID_CHAR = "'.'";
	private static final String VALID_INTEGER_REG = "-?\\d+";
	private static final String VALID_STRING = "\".*\"";

	/**
	 * enum that represents all of the valid variables types in S-java, with their matching regex expression
	 * for valid value
	 */
	public enum Type {
		BOOLEAN(VALID_BOOL) {
			@Override
			public String toString() {
				return "boolean";
			}
		},
		CHAR(VALID_CHAR) {
			@Override
			public String toString() {
				return "char";
			}
		},

		DOUBLE(VALID_DOUBLE) {
			@Override
			public String toString() {
				return "double";
			}
		},

		INT(VALID_INTEGER_REG) {
			@Override
			public String toString() {
				return "int";
			}
		},

		STRING(VALID_STRING) {
			@Override
			public String toString() {
				return "String";
			}
		};
		private final String myRegex;


		Type(String regex) {
			this.myRegex = regex;
		}


		String getRegex() {return this.myRegex;}

	}

	public enum General {
		VOID("void"), FINAL("final"), IF("if"), WHILE("while"), TRUE("true"), FALSE("false"),
		RETURN("return");
		private final String name;

		General(String s) {
			this.name = s;
		}

		String getName() {
			return this.name;
		}


		/**
		 * returns an Hashset containing all of the keywords in S-java
		 * @return S-java keywords hashSet
		 */
		public static HashSet<String> getKeywords() {
			HashSet<String> setToReturn = new HashSet<>();
			for (Type t : Type.values()) {
				setToReturn.add(t.toString());
			}
			for (General kw : General.values()) {
				setToReturn.add(kw.name);
			}
			return setToReturn;
		}
	}
}