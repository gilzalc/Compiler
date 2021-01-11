package oop.ex6;

import java.util.Arrays;
import java.util.regex.*;

public class TestClass {
	public static void main(String[] args) {
		//
		//
		//		// Get the regex to be checked
		//		String regex = " ?, ?";
		//
		//		Pattern pattern
		//				= Pattern.compile(regex);
		//
		//		// Get the String to be matched
		//		String stringToBeMatched
		//				= " Geeks=7,For=7,Geeks, fff ;";
		//
		//		Matcher matcher;
		//		String[] arr = stringToBeMatched.split(regex);
		//		regex = "(?<varName> ?_\\w+|[a-zA-Z]\\w* ?)(=(?<v> ?\\S ?\\S*))?";
		//		Pattern patt = Pattern.compile(regex);
		//		for (String s : arr) {
		//			matcher = patt.matcher(s);
		//			String firstWord = matcher.group("varName");
		////			String value = matcher.group("v");
		//			System.out.println(firstWord);
		//		}
		//		String s =" Geeks=7";
		//		String regex = "(?<varName> ?_\\w+|[a-zA-Z]\\w* ?)(=(?<v> ?\\S ?\\S*))?";
		//		Pattern pattern = Pattern.compile(regex);
		//		Matcher matcher = pattern.matcher(s);
		//		String var ="varName";
		//		String first = matcher.group(var);
		//
		// Get the regex to be checked
		String reg = " ?, ?";

		// Get the String to be matched
		String stringToBeMatched
				= " Geeks=7,For=7,Geeks, fff f;";

		String[] arr = stringToBeMatched.split(reg);
		String regex = "(?<varName> ?_\\w+|[a-zA-Z]\\w* ?)(=(?<v> ?\\S ?\\S*))?";

		// Create a pattern from regex
		Pattern pattern
				= Pattern.compile(regex);

		for (String s : arr) {
			Matcher matcher
					= pattern
					.matcher(s);

			// Get the current matcher state
			MatchResult result
					= matcher.toMatchResult();
			System.out.println("Current Matcher: "
							   + result);

			matcher.find();
			System.out.println(matcher.group("varName") + " " + matcher.group("v"));

		}
		pattern = Pattern.compile("(if|while) ?\\((.+)\\) ?\\{");
		Matcher matcher = pattern.matcher("if(56){");
		System.out.println(matcher.matches());
		System.out.println(matcher.group(2));
		pattern = Pattern.compile("(\\|\\|)|(&&)");
		matcher = pattern.matcher("twet||rey||rhr|Rherh");
		System.out.println(Arrays.toString("twetRherh".split("(\\|\\|)|(&&)")));

	}
}

