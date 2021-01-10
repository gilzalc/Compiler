package oop.ex6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
	private static final String FINAL_AND_FIRST_WORD_GROUPS = "(?<final>final )?(?<first>\\w+)";
	private static final String VAR_NAME_AND_VALUE
			= "(?<varName> ?_\\w+|[a-zA-Z]\\w* ?)(=(?<value> ?\\S?\\S*))?";
	private static final String COMMA_SEPARATED = " ?, ?";
	private static final String TRUE = "true";
	private static final String EQUALS = "=";
	private static final String FALSE = "false";
	private static final String VALID_VARIABLE_NAME = "_\\w+|[a-zA-Z]\\w*";// and not a keyword or typeword
	private static final String VALID_METHOD = "[a-zA-Z]\\w*";
	private static final String VALID_SUFFIX = "[{;]$";
	private static final String VALID_INTEGER = "-?\\d+";
	private static final String VALID_DOUBLE = "-?\\d+(\\.\\d+)?"; // W About .5 or 5. ?
	private static final String VALID_BOOL = TRUE + "|" + FALSE + "|" + VALID_DOUBLE;
	private static final String VALID_CHAR = "'.'";
	private static final String VALID_STRING = "\".*\"";
	private static final String PARENTHESES = "(.*)";
	private static final String SPACES = "\\s{2,}";
	private static final String SPACE_COMMENT = "^( //)";
	private static final String SPACE = "^ | $";
	private static final String EMPTY = "^\\s*$";
	private static final String COMMENT = "//.*";
	private String checkLine;

	public Regex(String line) {
		checkLine = line;
	}

	private Matcher regexMatcher(String p) {
		Pattern pattern = Pattern.compile(p);
		return pattern.matcher(checkLine);
	}

	public boolean commentOrEmpty() {
		return regexMatcher(EMPTY + "|" + COMMENT).matches();
	}

	public String checkSpaces() {
		checkLine = regexMatcher(SPACES).replaceAll(" ");
		if (regexMatcher(SPACE_COMMENT).find()) {
			return checkLine;
		}
		return regexMatcher(SPACE).replaceAll("");
	}

	public Matcher getFirstWords() {
		return regexMatcher(FINAL_AND_FIRST_WORD_GROUPS);
	}

	public static String[] getVarNameAndValue(java.lang.String s) {
		Pattern pat = Pattern.compile(VAR_NAME_AND_VALUE);
		Matcher matcher = pat.matcher(s);
		matcher.find();
		String nameString = matcher.group("varName");
		String valueString = matcher.group("value");
		return new String[] {
			 nameString,valueString
		};
	}

	public static boolean isValidVal(String pattern, String varVal) {
		return Pattern.matches(pattern, varVal);
	}

	public boolean enterScope() {
		return regexMatcher("{$").matches();
	}

	public String validSuffix(){
		if (regexMatcher(VALID_SUFFIX).find()){
			return regexMatcher(VALID_SUFFIX).replaceAll("");
		}
		return null;
	}

	public static boolean isVarNameValid(String var) {
		if (var.endsWith(" ")) {
			var = var.substring(0, var.length() - 1);
		}
		return (Pattern.matches(VALID_VARIABLE_NAME, var)) && !(Keywords.getKeywords().contains(var));
	}


	public String[] splitByComma() throws IllegalFileFormat {
		if (checkLine.endsWith(",")) {
			throw new IllegalFileFormat();
		}
		return this.checkLine.split(COMMA_SEPARATED);

	}
}
