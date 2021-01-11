package oop.ex6.regexs;

import oop.ex6.Regex;

import java.util.regex.Matcher;

public class VariableRegex extends Regex {
	Matcher matcher;
	protected static final String FINAL_AND_FIRST_WORD_GROUPS = "(?<final>final )?(?<type>\\w+) " +
																"(?<name>\\w+)";
	public VariableRegex(String line) {
		super(line);
		matcher = super.regexMatcher(FINAL_AND_FIRST_WORD_GROUPS);
	}
	public boolean isMatching(){
		return matcher.matches();
	}
	public boolean hasFinal(){
		return matcher.group(FINAL)!=null;
	}
	public String getStringType(){
		return matcher.group("type");
	}
	public String getStringName(){
		return matcher.group("name");
	}
}
