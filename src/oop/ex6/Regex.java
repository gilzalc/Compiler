package oop.ex6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
	private final static String SPACES = "\\s{2,}";
	private final static String EMPTY = "^\\s*$";
	private final static String COMMENT = "//.*";
	private final String checkLine;

	public Regex(String line){
		checkLine = line;
	}

	public String checkSpaces () {
		return regex(SPACES).replaceAll(" ");
	}

	public boolean commentOrEmpty(){
		return regex(EMPTY + "|" + COMMENT).matches();
	}

	private Matcher regex(String p){
		Pattern pattern = Pattern.compile(p);
		return pattern.matcher(checkLine);
	}
}
