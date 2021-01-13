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
		System.out.println(",".split(","));

		System.out.println("extraTest_001.sjava - 1\n" +
						   "extraTest_002.sjava - 0\n" +
						   "extraTest_003.sjava - 0\n" +
						   "extraTest_004.sjava - 1\n" +
						   "extraTest_005.sjava - 1\n" +
						   "extraTest_006.sjava - 1\n" +
						   "extraTest_007.sjava - 0\n" +
						   "extraTest_008.sjava - 0\n" +
						   "extraTest_009.sjava - 0\n" +
						   "extraTest_010.sjava - 1\n" +
						   "extraTest_011.sjava - 1\n" +
						   "extraTest_012.sjava - 1\n" +
						   "extraTest_013.sjava - 1\n" +
						   "extraTest_014.sjava - 0\n" +
						   "extraTest_015.sjava - 1\n" +
						   "extraTest_016.sjava - 1\n" +
						   "extraTest_017.sjava - 1\n" +
						   "extraTest_018.sjava - 1\n" +
						   "extraTest_019.sjava - 1\n" +
						   "extraTest_020.sjava - 0\n" +
						   "extraTest_021.sjava - 1\n" +
						   "extraTest_022.sjava - 1\n" +
						   "extraTest_023.sjava - 1\n" +
						   "extraTest_024.sjava - 1\n" +
						   "extraTest_025.sjava - 0\n" +
						   "extraTest_026.sjava - 1\n" +
						   "extraTest_027.sjava - 1\n" +
						   "extraTest_028.sjava - 1\n" +
						   "extraTest_029.sjava - 1\n" +
						   "extraTest_030.sjava - 0\n" +
						   "extraTest_031.sjava - 0\n" +
						   "extraTest_032.sjava - 1\n" +
						   "extraTest_033.sjava - 1\n" +
						   "extraTest_034.sjava - 1\n" +
						   "extraTest_035.sjava - 1\n" +
						   "extraTest_036.sjava - 1\n" +
						   "extraTest_037.sjava - 1\n" +
						   "extraTest_038.sjava - 1\n" +
						   "extraTest_039.sjava - 0\n" +
						   "extraTest_040.sjava - 1\n" +
						   "test001.sjava - 0\n" +
						   "test002.sjava - 0\n" +
						   "test003.sjava - 0\n" +
						   "test004.sjava - 0\n" +
						   "test005.sjava - 1\n" +
						   "test006.sjava - 1\n" +
						   "test007.sjava - 1\n" +
						   "test008.sjava - 1\n" +
						   "test009.sjava - 0\n" +
						   "test011.sjava - 0\n" +
						   "test012.sjava - 0\n" +
						   "test013.sjava - 0\n" +
						   "test014.sjava - 0\n" +
						   "test015.sjava - 0\n" +
						   "test016.sjava - 1\n" +
						   "test017.sjava - 1\n" +
						   "test018.sjava - 1\n" +
						   "test019.sjava - 0\n" +
						   "test021.sjava - 0\n" +
						   "test022.sjava - 0\n" +
						   "test023.sjava - 1\n" +
						   "test024.sjava - 1\n" +
						   "test025.sjava - 1\n" +
						   "test026.sjava - 1\n" +
						   "test027.sjava - 0\n" +
						   "test028.sjava - 0\n" +
						   "test029.sjava - 1\n" +
						   "test030.sjava - 0\n" +
						   "test031.sjava - 0\n" +
						   "test032.sjava - 0\n" +
						   "test033.sjava - 0\n" +
						   "test034.sjava - 0\n" +
						   "test035.sjava - 0\n" +
						   "test036.sjava - 1\n" +
						   "test037.sjava - 1\n" +
						   "test038.sjava - 1\n" +
						   "test039.sjava - 0\n" +
						   "test041.sjava - 0\n" +
						   "test042.sjava - 0\n" +
						   "test043.sjava - 0\n" +
						   "test044.sjava - 1\n" +
						   "test045.sjava - 1\n" +
						   "test046.sjava - 1\n" +
						   "test047.sjava - 1\n" +
						   "test048.sjava - 1\n" +
						   "test049.sjava - 1\n" +
						   "test050.sjava - 0\n" +
						   "test051.sjava - 0\n" +
						   "test052.sjava - 1\n" +
						   "test053.sjava - 1\n" +
						   "test054.sjava - 1\n" +
						   "test055.sjava - 1\n" +
						   "test056.sjava - 0\n" +
						   "test057.sjava - 1\n" +
						   "test058.sjava - 0\n" +
						   "test059.sjava - 0\n" +
						   "test061.sjava - 1\n" +
						   "test062.sjava - 1\n" +
						   "test063.sjava - 1\n" +
						   "test064.sjava - 1\n" +
						   "test065.sjava - 1\n" +
						   "test066.sjava - 1\n" +
						   "test067.sjava - 1\n" +
						   "test101.sjava - 1\n" +
						   "test102.sjava - 1\n" +
						   "test103.sjava - 1\n" +
						   "test104.sjava - 1\n" +
						   "test105.sjava - 1\n" +
						   "test106.sjava - 0\n" +
						   "test107.sjava - 0\n" +
						   "test108.sjava - 0\n" +
						   "test109.sjava - 0\n" +
						   "test110.sjava - 0\n" +
						   "test111.sjava - 0\n" +
						   "test112.sjava - 0\n" +
						   "test113.sjava - 0\n" +
						   "test114.sjava - 1\n" +
						   "test115.sjava - 1\n" +
						   "test116.sjava - 1\n" +
						   "test117.sjava - 1\n" +
						   "test201.sjava - 0\n" +
						   "test202.sjava - 0\n" +
						   "test203.sjava - 0\n" +
						   "test204.sjava - 0\n" +
						   "test205.sjava - 1\n" +
						   "test206.sjava - 1\n" +
						   "test207.sjava - 1\n" +
						   "test208.sjava - 1\n" +
						   "test209.sjava - 0\n" +
						   "test211.sjava - 0\n" +
						   "test212.sjava - 0\n" +
						   "test213.sjava - 0\n" +
						   "test214.sjava - 0\n" +
						   "test215.sjava - 0\n" +
						   "test216.sjava - 1\n" +
						   "test217.sjava - 1\n" +
						   "test218.sjava - 1\n" +
						   "test219.sjava - 0\n" +
						   "test221.sjava - 0\n" +
						   "test222.sjava - 0\n" +
						   "test223.sjava - 1\n" +
						   "test224.sjava - 1\n" +
						   "test225.sjava - 1\n" +
						   "test226.sjava - 1\n" +
						   "test227.sjava - 0\n" +
						   "test231.sjava - 0\n" +
						   "test232.sjava - 0\n" +
						   "test233.sjava - 0\n" +
						   "test234.sjava - 0\n" +
						   "test235.sjava - 0\n" +
						   "test236.sjava - 1\n" +
						   "test237.sjava - 1\n" +
						   "test238.sjava - 1\n" +
						   "test239.sjava - 0\n" +
						   "test241.sjava - 0\n" +
						   "test242.sjava - 0\n" +
						   "test243.sjava - 0\n" +
						   "test244.sjava - 1\n" +
						   "test245.sjava - 1\n" +
						   "test246.sjava - 1\n" +
						   "test247.sjava - 1\n" +
						   "test248.sjava - 1\n" +
						   "test249.sjava - 1\n" +
						   "test250.sjava - 1\n" +
						   "test251.sjava - 0\n" +
						   "test252.sjava - 1\n" +
						   "test253.sjava - 0\n" +
						   "test254.sjava - 1\n" +
						   "test255.sjava - 0\n" +
						   "test257.sjava - 0\n" +
						   "test261.sjava - 1\n" +
						   "test262.sjava - 1\n" +
						   "test263.sjava - 1\n" +
						   "test264.sjava - 1\n" +
						   "test265.sjava - 1\n" +
						   "test266.sjava - 0\n" +
						   "test267.sjava - 0\n" +
						   "test268.sjava - 1\n" +
						   "test269.sjava - 1\n" +
						   "test270.sjava - 1\n" +
						   "test271.sjava - 0\n" +
						   "test272.sjava - 1\n" +
						   "test273.sjava - 1\n" +
						   "test274.sjava - 1\n" +
						   "test291.sjava - 0\n" +
						   "test301.sjava - 0\n" +
						   "test302.sjava - 0\n" +
						   "test303.sjava - 0\n" +
						   "test305.sjava - 0\n" +
						   "test306.sjava - 1\n" +
						   "test307.sjava - 1\n" +
						   "test308.sjava - 1\n" +
						   "test309.sjava - 1\n" +
						   "test310.sjava - 1\n" +
						   "test311.sjava - 0\n" +
						   "test312.sjava - 0\n" +
						   "test313.sjava - 0\n" +
						   "test314.sjava - 1\n" +
						   "test315.sjava - 0\n" +
						   "test316.sjava - 1\n" +
						   "test321.sjava - 0\n" +
						   "test322.sjava - 0\n" +
						   "test323.sjava - 0\n" +
						   "test324.sjava - 0\n" +
						   "test325.sjava - 0\n" +
						   "test326.sjava - 0\n" +
						   "test327.sjava - 0\n" +
						   "test328.sjava - 1\n" +
						   "test401.sjava - 1\n" +
						   "test402.sjava - 1\n" +
						   "test403.sjava - 1\n" +
						   "test404.sjava - 0\n" +
						   "test405.sjava - 0\n" +
						   "test406.sjava - 1\n" +
						   "test407.sjava - 1\n" +
						   "test408.sjava - 1\n" +
						   "test409.sjava - 1\n" +
						   "test410.sjava - 0\n" +
						   "test411.sjava - 0\n" +
						   "test412.sjava - 0\n" +
						   "test413.sjava - 1\n" +
						   "test414.sjava - 0\n" +
						   "test415.sjava - 1\n" +
						   "test416.sjava - 1\n" +
						   "test417.sjava - 1\n" +
						   "test418.sjava - 0\n" +
						   "test420.sjava - 1\n" +
						   "test421.sjava - 1\n" +
						   "test422.sjava - 1\n" +
						   "test423.sjava - 1\n" +
						   "test425.sjava - 0\n" +
						   "test427.sjava - 0\n" +
						   "test428.sjava - 0\n" +
						   "test429.sjava - 1\n" +
						   "test430.sjava - 1\n" +
						   "test431.sjava - 1\n" +
						   "test432.sjava - 0\n" +
						   "test433.sjava - 0\n" +
						   "test434.sjava - 1\n" +
						   "test435.sjava - 1\n" +
						   "test436.sjava - 1\n" +
						   "test437.sjava - 0\n" +
						   "test438.sjava - 0\n" +
						   "test439.sjava - 1\n" +
						   "test440.sjava - 1\n" +
						   "test441.sjava - 1\n" +
						   "test442.sjava - 1\n" +
						   "test451.sjava - 0\n" +
						   "test452.sjava - 1\n" +
						   "test453.sjava - 1\n" +
						   "test454.sjava - 1\n" +
						   "test455.sjava - 1\n" +
						   "test456.sjava - 1\n" +
						   "test457.sjava - 1\n" +
						   "test458.sjava - 1\n" +
						   "test459.sjava - 1\n" +
						   "test460.sjava - 1\n" +
						   "test461.sjava - 0\n" +
						   "test462.sjava - 0\n" +
						   "test463.sjava - 1\n" +
						   "test464.sjava - 1\n" +
						   "test465.sjava - 0\n" +
						   "test466.sjava - 0\n" +
						   "test467.sjava - 1\n" +
						   "test468.sjava - 1\n" +
						   "test469.sjava - 0\n" +
						   "test470.sjava - 1\n" +
						   "test471.sjava - 1\n" +
						   "test472.sjava - 0\n" +
						   "test473.sjava - 0\n" +
						   "test474.sjava - 1\n" +
						   "test475.sjava - 1\n" +
						   "test476.sjava - 1\n" +
						   "test477.sjava - 1\n" +
						   "test501.sjava - 0\n" +
						   "test502.sjava - 1\n" +
						   "test503.sjava - 1\n" +
						   "test504.sjava - 1\n" +
						   "test505.sjava - 0".equals("extraTest_001.sjava - 1\n" +
													  "extraTest_002.sjava - 0\n" +
													  "extraTest_003.sjava - 0\n" +
													  "extraTest_004.sjava - 1\n" +
													  "extraTest_005.sjava - 1\n" +
													  "extraTest_006.sjava - 1\n" +
													  "extraTest_007.sjava - 0\n" +
													  "extraTest_008.sjava - 0\n" +
													  "extraTest_009.sjava - 0\n" +
													  "extraTest_010.sjava - 1\n" +
													  "extraTest_011.sjava - 1\n" +
													  "extraTest_012.sjava - 1\n" +
													  "extraTest_013.sjava - 1\n" +
													  "extraTest_014.sjava - 0\n" +
													  "extraTest_015.sjava - 1\n" +
													  "extraTest_016.sjava - 1\n" +
													  "extraTest_017.sjava - 1\n" +
													  "extraTest_018.sjava - 1\n" +
													  "extraTest_019.sjava - 1\n" +
													  "extraTest_020.sjava - 0\n" +
													  "extraTest_021.sjava - 1\n" +
													  "extraTest_022.sjava - 1\n" +
													  "extraTest_023.sjava - 1\n" +
													  "extraTest_024.sjava - 1\n" +
													  "extraTest_025.sjava - 0\n" +
													  "extraTest_026.sjava - 1\n" +
													  "extraTest_027.sjava - 1\n" +
													  "extraTest_028.sjava - 1\n" +
													  "extraTest_029.sjava - 1\n" +
													  "extraTest_030.sjava - 0\n" +
													  "extraTest_031.sjava - 0\n" +
													  "extraTest_032.sjava - 1\n" +
													  "extraTest_033.sjava - 1\n" +
													  "extraTest_034.sjava - 1\n" +
													  "extraTest_035.sjava - 1\n" +
													  "extraTest_036.sjava - 1\n" +
													  "extraTest_037.sjava - 1\n" +
													  "extraTest_038.sjava - 1\n" +
													  "extraTest_039.sjava - 0\n" +
													  "extraTest_040.sjava - 1\n" +
													  "test001.sjava - 0\n" +
													  "test002.sjava - 0\n" +
													  "test003.sjava - 0\n" +
													  "test004.sjava - 0\n" +
													  "test005.sjava - 1\n" +
													  "test006.sjava - 1\n" +
													  "test007.sjava - 1\n" +
													  "test008.sjava - 1\n" +
													  "test009.sjava - 0\n" +
													  "test011.sjava - 0\n" +
													  "test012.sjava - 0\n" +
													  "test013.sjava - 0\n" +
													  "test014.sjava - 0\n" +
													  "test015.sjava - 0\n" +
													  "test016.sjava - 1\n" +
													  "test017.sjava - 1\n" +
													  "test018.sjava - 1\n" +
													  "test019.sjava - 0\n" +
													  "test021.sjava - 0\n" +
													  "test022.sjava - 0\n" +
													  "test023.sjava - 1\n" +
													  "test024.sjava - 1\n" +
													  "test025.sjava - 1\n" +
													  "test026.sjava - 1\n" +
													  "test027.sjava - 0\n" +
													  "test028.sjava - 0\n" +
													  "test029.sjava - 1\n" +
													  "test030.sjava - 0\n" +
													  "test031.sjava - 0\n" +
													  "test032.sjava - 0\n" +
													  "test033.sjava - 0\n" +
													  "test034.sjava - 0\n" +
													  "test035.sjava - 0\n" +
													  "test036.sjava - 1\n" +
													  "test037.sjava - 1\n" +
													  "test038.sjava - 1\n" +
													  "test039.sjava - 0\n" +
													  "test041.sjava - 0\n" +
													  "test042.sjava - 0\n" +
													  "test043.sjava - 0\n" +
													  "test044.sjava - 1\n" +
													  "test045.sjava - 1\n" +
													  "test046.sjava - 1\n" +
													  "test047.sjava - 1\n" +
													  "test048.sjava - 1\n" +
													  "test049.sjava - 1\n" +
													  "test050.sjava - 0\n" +
													  "test051.sjava - 0\n" +
													  "test052.sjava - 1\n" +
													  "test053.sjava - 1\n" +
													  "test054.sjava - 1\n" +
													  "test055.sjava - 1\n" +
													  "test056.sjava - 0\n" +
													  "test057.sjava - 1\n" +
													  "test058.sjava - 0\n" +
													  "test059.sjava - 0\n" +
													  "test061.sjava - 1\n" +
													  "test062.sjava - 1\n" +
													  "test063.sjava - 1\n" +
													  "test064.sjava - 1\n" +
													  "test065.sjava - 1\n" +
													  "test066.sjava - 1\n" +
													  "test067.sjava - 1\n" +
													  "test101.sjava - 1\n" +
													  "test102.sjava - 1\n" +
													  "test103.sjava - 1\n" +
													  "test104.sjava - 1\n" +
													  "test105.sjava - 1\n" +
													  "test106.sjava - 0\n" +
													  "test107.sjava - 0\n" +
													  "test108.sjava - 0\n" +
													  "test109.sjava - 0\n" +
													  "test110.sjava - 0\n" +
													  "test111.sjava - 0\n" +
													  "test112.sjava - 0\n" +
													  "test113.sjava - 0\n" +
													  "test114.sjava - 1\n" +
													  "test115.sjava - 1\n" +
													  "test116.sjava - 1\n" +
													  "test117.sjava - 1\n" +
													  "test201.sjava - 0\n" +
													  "test202.sjava - 0\n" +
													  "test203.sjava - 0\n" +
													  "test204.sjava - 0\n" +
													  "test205.sjava - 1\n" +
													  "test206.sjava - 1\n" +
													  "test207.sjava - 1\n" +
													  "test208.sjava - 1\n" +
													  "test209.sjava - 0\n" +
													  "test211.sjava - 0\n" +
													  "test212.sjava - 0\n" +
													  "test213.sjava - 0\n" +
													  "test214.sjava - 0\n" +
													  "test215.sjava - 0\n" +
													  "test216.sjava - 1\n" +
													  "test217.sjava - 1\n" +
													  "test218.sjava - 1\n" +
													  "test219.sjava - 0\n" +
													  "test221.sjava - 0\n" +
													  "test222.sjava - 0\n" +
													  "test223.sjava - 1\n" +
													  "test224.sjava - 1\n" +
													  "test225.sjava - 1\n" +
													  "test226.sjava - 1\n" +
													  "test227.sjava - 0\n" +
													  "test231.sjava - 0\n" +
													  "test232.sjava - 0\n" +
													  "test233.sjava - 0\n" +
													  "test234.sjava - 0\n" +
													  "test235.sjava - 0\n" +
													  "test236.sjava - 1\n" +
													  "test237.sjava - 1\n" +
													  "test238.sjava - 1\n" +
													  "test239.sjava - 0\n" +
													  "test241.sjava - 0\n" +
													  "test242.sjava - 0\n" +
													  "test243.sjava - 0\n" +
													  "test244.sjava - 1\n" +
													  "test245.sjava - 1\n" +
													  "test246.sjava - 1\n" +
													  "test247.sjava - 1\n" +
													  "test248.sjava - 1\n" +
													  "test249.sjava - 1\n" +
													  "test250.sjava - 1\n" +
													  "test251.sjava - 0\n" +
													  "test252.sjava - 1\n" +
													  "test253.sjava - 0\n" +
													  "test254.sjava - 1\n" +
													  "test255.sjava - 0\n" +
													  "test257.sjava - 1\n" +
													  "test261.sjava - 1\n" +
													  "test262.sjava - 1\n" +
													  "test263.sjava - 1\n" +
													  "test264.sjava - 1\n" +
													  "test265.sjava - 1\n" +
													  "test266.sjava - 0\n" +
													  "test267.sjava - 0\n" +
													  "test268.sjava - 1\n" +
													  "test269.sjava - 1\n" +
													  "test270.sjava - 1\n" +
													  "test271.sjava - 0\n" +
													  "test272.sjava - 1\n" +
													  "test273.sjava - 1\n" +
													  "test274.sjava - 1\n" +
													  "test291.sjava - 0\n" +
													  "test301.sjava - 0\n" +
													  "test302.sjava - 0\n" +
													  "test303.sjava - 0\n" +
													  "test305.sjava - 0\n" +
													  "test306.sjava - 1\n" +
													  "test307.sjava - 1\n" +
													  "test308.sjava - 1\n" +
													  "test309.sjava - 1\n" +
													  "test310.sjava - 1\n" +
													  "test311.sjava - 0\n" +
													  "test312.sjava - 0\n" +
													  "test313.sjava - 0\n" +
													  "test314.sjava - 1\n" +
													  "test315.sjava - 0\n" +
													  "test316.sjava - 1\n" +
													  "test321.sjava - 0\n" +
													  "test322.sjava - 0\n" +
													  "test323.sjava - 0\n" +
													  "test324.sjava - 0\n" +
													  "test325.sjava - 0\n" +
													  "test326.sjava - 0\n" +
													  "test327.sjava - 0\n" +
													  "test328.sjava - 1\n" +
													  "test401.sjava - 1\n" +
													  "test402.sjava - 1\n" +
													  "test403.sjava - 1\n" +
													  "test404.sjava - 0\n" +
													  "test405.sjava - 0\n" +
													  "test406.sjava - 1\n" +
													  "test407.sjava - 1\n" +
													  "test408.sjava - 1\n" +
													  "test409.sjava - 1\n" +
													  "test410.sjava - 0\n" +
													  "test411.sjava - 0\n" +
													  "test412.sjava - 0\n" +
													  "test413.sjava - 1\n" +
													  "test414.sjava - 0\n" +
													  "test415.sjava - 1\n" +
													  "test416.sjava - 1\n" +
													  "test417.sjava - 1\n" +
													  "test418.sjava - 0\n" +
													  "test420.sjava - 1\n" +
													  "test421.sjava - 1\n" +
													  "test422.sjava - 1\n" +
													  "test423.sjava - 1\n" +
													  "test425.sjava - 0\n" +
													  "test427.sjava - 0\n" +
													  "test428.sjava - 0\n" +
													  "test429.sjava - 1\n" +
													  "test430.sjava - 1\n" +
													  "test431.sjava - 1\n" +
													  "test432.sjava - 0\n" +
													  "test433.sjava - 0\n" +
													  "test434.sjava - 1\n" +
													  "test435.sjava - 1\n" +
													  "test436.sjava - 1\n" +
													  "test437.sjava - 0\n" +
													  "test438.sjava - 0\n" +
													  "test439.sjava - 1\n" +
													  "test440.sjava - 1\n" +
													  "test441.sjava - 1\n" +
													  "test442.sjava - 1\n" +
													  "test451.sjava - 0\n" +
													  "test452.sjava - 1\n" +
													  "test453.sjava - 1\n" +
													  "test454.sjava - 1\n" +
													  "test455.sjava - 1\n" +
													  "test456.sjava - 1\n" +
													  "test457.sjava - 1\n" +
													  "test458.sjava - 1\n" +
													  "test459.sjava - 1\n" +
													  "test460.sjava - 1\n" +
													  "test461.sjava - 0\n" +
													  "test462.sjava - 0\n" +
													  "test463.sjava - 1\n" +
													  "test464.sjava - 1\n" +
													  "test465.sjava - 0\n" +
													  "test466.sjava - 0\n" +
													  "test467.sjava - 1\n" +
													  "test468.sjava - 1\n" +
													  "test469.sjava - 0\n" +
													  "test470.sjava - 1\n" +
													  "test471.sjava - 1\n" +
													  "test472.sjava - 0\n" +
													  "test473.sjava - 0\n" +
													  "test474.sjava - 1\n" +
													  "test475.sjava - 1\n" +
													  "test476.sjava - 1\n" +
													  "test477.sjava - 1\n" +
													  "test501.sjava - 0\n" +
													  "test502.sjava - 1\n" +
													  "test503.sjava - 1\n" +
													  "test504.sjava - 1\n" +
													  "test505.sjava - 0"));

	}
}

