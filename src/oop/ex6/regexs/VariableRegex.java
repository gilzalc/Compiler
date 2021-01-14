package oop.ex6.regexs;

import java.util.regex.Matcher;

/**
 * An object that its responsible is to manipulate variable strings using regex
 */
public class VariableRegex extends Regex {
	// matcher object of the class
	private final Matcher matcher;
	private static final String FINAL_AND_FIRST_WORD_GROUPS = "(?<final>final )?(?<type>\\w+) " +
																"(?<name>\\w+)";

	/**
	 * constructor for the variable regex, in addition its sets him a matcher object with a variable line
	 * pattern
	 * @param line line to set as the String to manipulate
	 */
	public VariableRegex(String line) {
		super(line);
		matcher = super.regexMatcher(FINAL_AND_FIRST_WORD_GROUPS);
	}

	/**
	 * @return true if the matcher matches(and creates the groups) , false O.W
	 */
	public boolean isMatching(){
		return matcher.matches();
	}

	/**
	 * @return true if the variable line starts with the word "final" , false o.w
	 */
	public boolean hasFinal(){
		return matcher.group(FINAL)!=null;
	}

	/**
	 * @return the String corresponding to the type of the variable (supposedly)
	 */
	public String getStringType(){
		return matcher.group("type");
	}

	/**
	 * @return the String corresponding to the name of the variable (supposedly)
	 */
	public String getStringName(){
		return matcher.group("name");
	}
}
