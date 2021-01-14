import oop.ex6.main.Sjavac;

// import the library - alt+enter
import static org.junit.Assert.*;

import org.junit.Test;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Tester_ex6_oop {
	private static Path pathToFiles = Paths.get("src", "tester_files");
	private static Path pathToTests = Paths.get(pathToFiles.toString(), "tests");
	private static Path pathToUserTests = Paths.get(pathToTests.toString(), "specificTests");
	private static Path pathToOutputFile = Paths.get(pathToFiles.toString(), "user_output.txt");
	private static Path pathToSchoolSolution = Paths.get(pathToFiles.toString(), "school_solution.jar");
//	private static Path pathToMainClass = Paths.get("oop", "ex5", "main", "Sjava.java");
//	private static Path pathToCompiledFiles = Paths.get(pathToFiles.toString(), "compiled_files");

	private static PrintStream originalOutStream = System.out;
	private static PrintStream originalErrStream = System.err;
	private static ByteArrayOutputStream baosP = new ByteArrayOutputStream();
	private static ByteArrayOutputStream baosE = new ByteArrayOutputStream();
	private static PrintStream printsRecorder = new PrintStream(baosP);
	private static PrintStream ErrorsRecorder = new PrintStream(baosE);


	private static String error = "\n" +
			"▓█████  ██▀███   ██▀███   ▒█████   ██▀███  \n" +
			"▓█   ▀ ▓██ ▒ ██▒▓██ ▒ ██▒▒██▒  ██▒▓██ ▒ ██▒\n" +
			"▒███   ▓██ ░▄█ ▒▓██ ░▄█ ▒▒██░  ██▒▓██ ░▄█ ▒\n" +
			"▒▓█  ▄ ▒██▀▀█▄  ▒██▀▀█▄  ▒██   ██░▒██▀▀█▄  \n" +
			"░▒████▒░██▓ ▒██▒░██▓ ▒██▒░ ████▓▒░░██▓ ▒██▒\n" +
			"░░ ▒░ ░░ ▒▓ ░▒▓░░ ▒▓ ░▒▓░░ ▒░▒░▒░ ░ ▒▓ ░▒▓░\n" +
			" ░ ░  ░  ░▒ ░ ▒░  ░▒ ░ ▒░  ░ ▒ ▒░   ░▒ ░ ▒░\n" +
			"   ░     ░░   ░   ░░   ░ ░ ░ ░ ▒    ░░   ░ \n" +
			"   ░  ░   ░        ░         ░ ░     ░     \n" +
			"                                           ";


	@Test
	/*
	optionals - run specific tests.
	To use this:
		1. place Sjava files in 'Tests/specificTests' folder.
		2. press the little green arrow here to the left.
		<---
	 */
	public void runSpecificTests_optional() {
		int i = 1;
		LinkedList<File> listOfTests = getListOfTests(pathToUserTests);
		if (listOfTests.size() == 0) { // not files were places.
			System.out.println("If you want to use runSpecificTests_optional, you have to add test files to" +
					"'Tests\\specificTests'.");
		}
		for (File file : listOfTests) {
			Path testPath = Paths.get(file.getPath());
			System.out.printf(String.format("\nstarting test %s, %%d out of %%d:%%n",
					getTestName(Paths.get(file.getPath()))), i, listOfTests.size());
			String[] userPrints = runTestWithOnUser(testPath.toString());
			String[] schoolPrints = runTestWithSchoolSolution(testPath.toString());
			System.out.println("school solution result:");
			System.out.println(schoolPrints[0]); //prints
			System.out.println(schoolPrints[1]); //errors
			System.out.println();
			System.out.println("user result:");
			System.out.println(userPrints[0]); //prints
			System.out.println(userPrints[1]); //errors

		}


	}


	@Test
	/*
	main test - run all tests.
	 */
	public void runAllTests() {
		resetOutputFile();

		System.out.println("The results of this test are saved in the file user_output");
		boolean passedAll = true;

		int numOfPassed = 0, testIndex = 1;
		LinkedList<File> listOfTests = getListOfTests(pathToTests);
		for (File file : listOfTests) {
			System.out.println();
			System.out.printf(String.format("starting test %s, %%d out of %%d:%%n",
					getTestName(Paths.get(file.getPath()))), testIndex, listOfTests.size());
			if (doOneTest(Paths.get(file.getPath()))) // the real thing
				numOfPassed++;
			else
				passedAll = false;
			testIndex++;
		}

		// massages. nothing critical from here.

		String summary = String.format("\npassed %d out of %d tests.%n", numOfPassed, listOfTests.size());
		System.out.println(summary);
		writeToFile(summary);
		assertTrue(error, passedAll);

		try {
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().browse(new URI("http://mrwgifs.com/wp-content/uploads/2013/08/Success-Kid-Meme-Gif.gif"));
			}
		} catch (Exception e) {
		}
		System.out.println("The results of this test are saved in the file user_output");
	}

	@Test
	/*
	test for type 2 errors.
	 */
	public void runType2ErrorTests() {
		System.out.println("\nstarting runType2ErrorTests:");
		System.out.println("Make sure school-solution massage and user massage talk about the same thing.");

		String pathToTestFile1 =
				Paths.get(pathToTests.toString(), "SchoolTests", "test001.sjava").toString();
		String pathToTestFile2 =
				Paths.get(pathToTests.toString(), "SchoolTests", "test002.sjava").toString();

		String[][] args = new String[2][];
		//args[0] = new String[]{"", "No args"}; // school solution throw
		args[0] = new String[]{pathToTestFile1 + " " + pathToTestFile2, "2 args"};
		args[1] = new String[]{pathToTests.toString(), "not a file"};

		for (String[] arr : args) {
			System.out.println();
			System.out.println("type 2 tested: " + arr[1]);
			try {
				String[] userPrints = runTestWithOnUser(arr[0]);
				String[] SchoolPrints = runTestWithSchoolSolution(arr[0]);
				assertTrue(SchoolPrints[0].equals(userPrints[0])); // both should be 2.
				System.out.println("school_solution error massage:");
				System.out.println(userPrints[1]);
				System.out.println("user massage:");
				System.out.println(SchoolPrints[1]);
			} catch (Exception e) {
				System.err.println("your program throw an exception (and that's not OK).");
				e.printStackTrace();
				fail();
			}
		}

	}

//	@Test //TODO
//	public void checkCompilationAndPrintFormat(){
//		compileFiles();
//	}
//
//	private void compileFiles(){
//		BufferedReader msg = excCommand("javac", "-d", pathToCompiledFiles.toString(), "-cp", "src",
//				pathToMainClass.toString());
//		System.out.println(msg.lines().collect(Collectors.joining()));
//
//	}

	/*
	return all tests Files in the directory (also in sub and sub-sub directions).
	 */
	private LinkedList<File> getListOfTests(Path testsPath) {
		LinkedList<File> listOfTests = new LinkedList<>();

		File testsMainFolder = new File(testsPath.toString());
		File[] filesArr = testsMainFolder.listFiles(File::isFile); // folder
		if (filesArr != null)
			listOfTests.addAll(Arrays.asList(filesArr));
		File[] folders = testsMainFolder.listFiles(File::isDirectory);
		if (folders == null) return listOfTests;
		for (File folder : folders) {
			filesArr = folder.listFiles(File::isFile); // sub-folder
			if (filesArr == null) continue;
			listOfTests.addAll(Arrays.asList(filesArr));

			File subFolders[] = folder.listFiles(File::isDirectory);
			if (subFolders == null) continue;

			for (File subFolder : subFolders) {
				filesArr = subFolder.listFiles(File::isFile); // sub-sub-folder
				if (filesArr == null) continue;
				listOfTests.addAll(Arrays.asList(filesArr));
			}
		}
		return listOfTests;
	}

	/*
	for given test - run school solution and user solution, compare, print and write about it.
	 */
	private boolean doOneTest(Path pathToTest) {
		String[] SchoolSolutionOutput = runTestWithSchoolSolution(pathToTest.toString());
		String[] userOutput = runTestWithOnUser(pathToTest.toString());


		// printed to match
		if (userOutput[0].length() != 1) {
			String toPrint = "while testing " + getTestName(pathToTest) +
					", Your program printed something that is not '1' or '0' or '2'.\n" +
					"The prints were:\n" + userOutput[0] + "\n" + userOutput[1];
			System.err.println(toPrint);
			writeToFile("\n" + toPrint + "\n");
			return false;

		}

		boolean passed = SchoolSolutionOutput[0].equals(userOutput[0]); // compare
		writeResultToFile(pathToTest, SchoolSolutionOutput[0], userOutput[0], passed);

		if (passed) {
			System.out.println("passed :)");
			return true;
		} else {
			System.out.println("failed\t\t\t\t\t\t#######################################################");
			return false;
		}
	}

	/*
	reset output file
	 */
	private void resetOutputFile() {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(pathToOutputFile.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		writer.print(sdf.format(c.getTime()));
		writer.print("\n\n");
		writer.print("school solution#User solution#result#Test name");
		writer.print("\n\n");

		writer.close();
	}

	/*
	write result of test
	 */
	private void writeResultToFile(
			Path path, String SchoolSolutionOutput, String UserOutput, boolean passed) {

		String isPassed;
		String mark;
		if (passed) {
			isPassed = "passed";
			mark = "";
		} else {
			isPassed = "failed";
			mark = "##################################";
		}


		String lineToWrite =
				String.format("%s\t\t%s\t\t%s\t\t%s\t\t%s",
						SchoolSolutionOutput, UserOutput.trim(), isPassed, getTestName(path), mark);
		writeToFile(lineToWrite);
	}

	/*
	write down the given string on the output file.
	 */
	private void writeToFile(String toWrite) {
		try (FileWriter fw = new FileWriter(pathToOutputFile.toString(), true);
			 BufferedWriter bw = new BufferedWriter(fw);
			 PrintWriter out = new PrintWriter(bw)) {
			out.println(toWrite);
		} catch (IOException e) {
			fail("problem with output file, should not happen.");
		}
	}

	/*
	return the name of the test (cut off the prefix)
	 */
	private String getTestName(Path pathTOTest) {
		return pathTOTest.toString().substring(pathTOTest.toString().lastIndexOf("tests") + 5);
	}

	/*
	return the output of the user's program on the given test.
	 */
	private String[] runTestWithOnUser(String pathTOTest) {
		recordPrints();
		try {
			Sjavac.main(new String[]{pathTOTest});
		} catch (Exception e) {
			// your program threw something...
			e.printStackTrace();
		}
		return getPrintsAndBackToNormal();
	}

	/*
	return the output of the school solution on the given test.
	 */
	private String[] runTestWithSchoolSolution(String pathTOTest) {
		return excCommand("java", "-jar", pathToSchoolSolution.toString(), pathTOTest);
	}


	// ----------------------------------- not interesting -------------------------------------------------

	/*
	run the command given is cmd and return output
	 */
	private String[] excCommand(String... args) {
		String[] records = new String[2];
		ProcessBuilder builder = new ProcessBuilder(args);
		try {
			Process p = builder.start();
			BufferedReader out = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader err = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			records[0] = out.lines().collect(Collectors.joining());
			records[1] = err.lines().collect(Collectors.joining());
		} catch (IOException e) {
			// download tester again.
			e.printStackTrace();
			return null;
		}
		return records;
	}


	/*
	record all prints.
	 */
	private static void recordPrints() {
		System.setOut(printsRecorder);
		System.setErr(ErrorsRecorder);
	}

	/*
	return recorded prints
	 */
	private static String[] getPrintsAndBackToNormal() {
		String[] records = new String[2];
		records[0] = baosP.toString().trim();
		baosP.reset();
		System.setOut(originalOutStream);

		records[1] = baosE.toString().trim();
		baosE.reset();
		System.setErr(originalErrStream);

		return records;
	}

	/*
	return recorded prints
	 */
	private static String getErrorsAndBackToNormal() {
		String string = baosE.toString();
		baosE.reset();
		System.setErr(originalErrStream);

		return string;
	}


}
