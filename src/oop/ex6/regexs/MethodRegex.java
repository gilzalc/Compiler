package oop.ex6.regexs;

import java.util.regex.Matcher;

/**
 * An object that its responsible is to manipulate methods strings using regex
 */
public class MethodRegex extends Regex {

	private static final String STARTS_WITH_VOID = "(\\s?void ){1}?"; //uses reluctant search
	private static final String METHOD_PARAMS
			= "^(?<name>[a-zA-Z]\\w*)\\s?\\((?<parameters>[^\\(\\)]*)\\)\\s*\\{$";


	public MethodRegex(String line) {
		super(line);
	}

	/**
	 * @return true if the method starts legally, and takes off her first word false o.w
	 */
	public boolean methodStart() {
		Matcher matcher = regexMatcher(STARTS_WITH_VOID);
		if (matcher.lookingAt()) {
			int newStart = matcher.end();
			checkLine = checkLine.substring(newStart);
			return true;
		}
		return false;
	}

	/**
	 * tries to match the method name and parameters,
	 * @return a String[] array that holds in the first place the group that matches the name, and in the
	 * 		second the group that matches the parameters
	 */
	public String[] getMethodNameParams() {
		Matcher matcher = regexMatcher(METHOD_PARAMS);
		if (matcher.matches()) {
			return new String[]{matcher.group(NAME), matcher.group(PARAMETERS)};
		}
		return null; //will be exception - not a valid method line
	}
}
