package oop.ex6.regexs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class that is responsible for all of the general regex manipulation of the text of the S-java file
 */
public class Regex {

	//-----------------------------magic numbers-----------------\\
	protected static final String FINAL = "final";
	protected static final String PARAMETERS = "parameters";
	protected static final String NAME = "name";
	private static final String FIRST = "first";
	private static final String VALUE = "value";
	private static final String CONDITION = "condition";

	//-----------------------------Capturing groups Regex's-----------------\\
	private static final String FINAL_AND_FIRST_WORD_GROUPS = "((?<final>final )?(?<first>\\w+))";
	private static final String VAR_NAME_AND_VALUE = " ?(?<name>_\\w+|[a-zA-Z]\\w*) ?(= ?(?<value>((\\\"" +
													 " ?\\S* ?\\\")|(\\S*))))?";

	//-----------------------------regex's--------------------------\\
	private static final String METHOD_CALL = "^(?<name>[a-zA-Z]\\w*)\\s?\\((?<parameters>.*)\\)\\s*$";
	private static final String COMMA_SEPARATED = "\\s?,\\s?";
	private static final String AND_OR = "\\s?(\\|\\|)\\s?|\\s?(&&) ?";
	private static final String IF_WHILE = "(if|while)\\s?\\((?<condition>.+)\\)\\s*\\{";
	private static final String VALID_VARIABLE_NAME = "_\\w+|[a-zA-Z]\\w*";// and not a keyword or a type
	private static final String VALID_SUFFIX = "\\s*;$";
	private static final String SPACES = "\\b\\s{2,}|\\s{2,}\\b";
	private static final String SPACE_COMMENT = "^(\\s//)";
	private static final String SPACE = "^\\s+|\\s+$";
	private static final String EMPTY_OR_COMMENT = "^\\s*$|//.*";
	private static final String RETURN = "return\\s?";
	private static final String OPEN_BLOCK = "\\{$";
	private static final String COMMA = ",";
	private static final String ONE_SPACE = " ";
	private static final String NO_SPACE = "";
	private static final String OPTIONAL_SPACE = " ?";

	//--------------DATA MEMBERS-------------\\
	private Matcher firstWordsMatcher;
	/**
	 * The string to manipulate with regex
	 */
	protected String checkLine;


	public Regex(String line) {
		checkLine = line;
	}

	/**
	 * Sets the string to search with the regex
	 * @param checkLine new String to set as check line
	 */
	public void setCheckLine(String checkLine) {
		this.checkLine = checkLine;
	}

	protected Matcher regexMatcher(String p) {
		Pattern pattern = Pattern.compile(p);
		return pattern.matcher(checkLine);
	}

	public boolean commentOrEmpty() {
		return regexMatcher(EMPTY_OR_COMMENT).matches();
	}

	public String checkSpaces() {
		checkLine = regexMatcher(SPACES).replaceAll(ONE_SPACE);
		if (regexMatcher(SPACE_COMMENT).find()) {
			return checkLine;
		}
		return startEndSpace();
	}

	public String startEndSpace() {
		return regexMatcher(SPACE).replaceAll(NO_SPACE);
	}

	public String[] checkMethodCall() {
		Matcher matcher = (regexMatcher(METHOD_CALL));
		if (matcher.matches()) {
			return new String[]{matcher.group(NAME), matcher.group(PARAMETERS)};
		}
		return null;
	}

	public boolean setFirstWordsMatcher() {
		firstWordsMatcher = regexMatcher(FINAL_AND_FIRST_WORD_GROUPS);
		return firstWordsMatcher.find();
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
		if (matcher.matches()) {
			String nameString = matcher.group(NAME);
			String valueString = matcher.group(VALUE);
			return new String[]{nameString, valueString};
		}
		return null;
	}

	public boolean enterBlock() {
		return regexMatcher(OPEN_BLOCK).find();
	}

	public String validSuffix() {
		if (regexMatcher(VALID_SUFFIX).find()) {
			return regexMatcher(VALID_SUFFIX).replaceAll(NO_SPACE);
		}
		return null;
	}

	public String[] splitDeclarations() {
		if (checkLine.endsWith(COMMA)) {
			return null;
		}
		return splitByComma();
	}

	/**
	 * Splits the string by the COMMA_SEPARATED regex
	 * @return an array of the split string
	 */
	public String[] splitByComma() {
		checkLine = startEndSpace();
		return checkLine.split(COMMA_SEPARATED);
	}

	public String[] splitCondition() {
		checkLine = startEndSpace();
		return checkLine.split(AND_OR);
	}

	public boolean emptyLine() {
		return regexMatcher(OPTIONAL_SPACE).matches();
	}

	public boolean isReturnLine() {
		return regexMatcher(RETURN).matches();
	}

	public String ifWhileCondition() {
		Matcher matcher = regexMatcher(IF_WHILE);
		if (matcher.matches()) {
			return matcher.group(CONDITION);
		}
		return null;
	}

	//------------------Static  Regex methods------------------\\
	public static boolean isVarNameValid(String varName) {
		if (varName.endsWith(ONE_SPACE)) {
			varName = varName.substring(0, varName.length() - 1);
		}
		return (Pattern.matches(VALID_VARIABLE_NAME, varName));
	}

	public static boolean isValidVal(String pattern, String varVal) {
		return Pattern.matches(pattern, varVal);
	}
}
