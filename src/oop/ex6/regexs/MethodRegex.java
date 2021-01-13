package oop.ex6.regexs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodRegex extends Regex {

	public MethodRegex(String line) {
		super(line);
	}

//	public static boolean isValidMethodName(String methodName) {
//		return (Pattern.matches(VALID_METHOD_NAME, methodName));
//	}

	public boolean methodStart() {
		Matcher matcher = regexMatcher(STARTS_WITH_VOID);
		if (matcher.lookingAt()) {
			int newStart = matcher.end();
			checkLine = checkLine.substring(newStart);
			return true;
		}
		return false;
	}
	public String getMethodName(){
		Matcher matcher = regexMatcher(VALID_METHOD_NAME);
		if (matcher.find()){
			return checkLine.substring(matcher.start(),matcher.end());
		}
		System.out.println("error");
		return ""; // Error?
	}
	public String getMethodParameters() {
		Matcher matcher = regexMatcher(METHOD_PARAMS);
		if(matcher.matches()){
			return matcher.group(parameters);
		}
		System.out.println("error");
		return ""; //error
	}
}
