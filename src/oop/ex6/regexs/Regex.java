package oop.ex6.regexs;

import oop.ex6.main.IllegalSFile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
	//-----------------------------magic numbers-----------------\\
	protected static final String FINAL = "final";
	protected static final String FIRST = "first";
	private static final String TRUE = "true";
	private static final String EQUALS = "=";
	private static final String FALSE = "false";
	private static final String WHILE = "while";
	protected static final String parameters = "parameters";
	//-----------------------------Capturing groups-----------------\\
	protected static final String FINAL_AND_FIRST_WORD_GROUPS = "(?<final>final )?(?<first>\\w+) ";
	private static final String VAR_NAME_AND_VALUE
			= "(?<varName> ?_\\w+|[a-zA-Z]\\w* ?)(=(?<value> ?\\S?\\S*))?";// למה יש פעמיים \\S בסוף?
	protected static final String METHOD_PARAMS = " ?\\((?<parameters>[^\\(\\)]*)\\)\\s*";
	//-----------------------------regex-----------------\\
	protected static final String METHOD_NAME = "[^\\s\\(]+";
	private static final String METHOD_CALL = "^([^\\s\\(=]+) ?\\((.*\\)) ?$";
	protected static final String STARTS_WITH_VOID = " ?void ";
	private static final String COMMA_SEPARATED = " ?, ?";
	private static final String IF_While = "(if|while) ?\\((.+)\\) ?\\{";
	private static final String VALID_VARIABLE_NAME = "_\\w+|[a-zA-Z]\\w*";// and not a keyword or typeword
	protected static final String VALID_METHOD_NAME = "[a-zA-Z]\\w*";
	private static final String VALID_SUFFIX = ";$";
	private static final String VALID_INTEGER = "-?\\d+";
	private static final String VALID_DOUBLE = "-?\\d+(\\.\\d+)?"; // W About .5 or 5. ?
	private static final String VALID_BOOL = TRUE + "|" + FALSE + "|" + VALID_DOUBLE;
	private static final String VALID_CHAR = "'.'";
	private static final String VALID_STRING = "\".*\"";
	private static final String PARENTHESES = "(.*)";
//	private static final String NOT_VALID_CHAR = "'\\s{2,}'";
	private static final String SPACES = "\\b\\s{2,}|\\s{2,}\\b";
	private static final String SPACE_COMMENT = "^( //)";
	private static final String SPACE = "^ | $";
	private static final String EMPTY = "^\\s*$";
	private static final String COMMENT = "//.*";
	private static final String RETURN = "return ?";

	//--------------DATA MEMBERS-------------\\
	private Matcher firstWordsMatcher;
	protected String checkLine;


	public Regex(String line) {
		checkLine = line;
	}

	protected Matcher regexMatcher(String p) {
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
		return startEndSpace();
	}

	public String startEndSpace(){
		return regexMatcher(SPACE).replaceAll("");
	}

	public String[] checkMethodCall(){
		Matcher matcher = (regexMatcher(METHOD_CALL));
		if (matcher.matches()) {
			return new String[]{matcher.group(1), matcher.group(2)};
		}
		return null;
	}


	public void setFirstWordsMatcher() {
		firstWordsMatcher = regexMatcher(FINAL_AND_FIRST_WORD_GROUPS);
	}

	public String getFirstWord() {
		return firstWordsMatcher.group(FIRST);
	}

	public boolean hasFinal() {
		return firstWordsMatcher.group(FINAL) != null;
	}

	public int getEndOfFirst() {
		return firstWordsMatcher.end();
	}

	public String[] getVarNameAndValue() {
		Matcher matcher = regexMatcher(VAR_NAME_AND_VALUE);
		if (matcher.find()) {
			String nameString = matcher.group("varName");
			String valueString = matcher.group("value");
			return new String[]{nameString, valueString};
		}
		// throw an error?
		return new String[]{"S"}; //to edit!!!!!
	}


	public boolean enterScope() {
		return regexMatcher("{$").matches();
	}

	public String validSuffix() {
		if (regexMatcher(VALID_SUFFIX).find()) {
			return regexMatcher(VALID_SUFFIX).replaceAll("");
		}
		return null;
	}

	public String[] splitByComma() {
//		if (checkLine.endsWith(",")) {
//			throw new IllegalSFile();
//		}
		return this.checkLine.split(COMMA_SEPARATED);
	}

	public boolean isReturnLine(){
		return regexMatcher(RETURN).matches();
	}

	public String ifWhileCondition() {
		Matcher matcher = regexMatcher(IF_While);
		if (matcher.matches()) {
			return matcher.group(2);//condition
		}
		return null;
	}

	//------------------Static methods------------------\\
	public static boolean isVarNameValid(String varName) {
		if (varName.endsWith(" ")) {
			varName = varName.substring(0, varName.length() - 1);
		}
		return (Pattern.matches(VALID_VARIABLE_NAME, varName));
	}

	public static boolean isValidMethodName(String methodName) {
		return (Pattern.matches(VALID_METHOD_NAME, methodName));
	}

	public static boolean isValidVal(String pattern, String varVal) {
		return Pattern.matches(pattern, varVal);
	}

}