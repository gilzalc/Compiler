import java.io.File;

import oop.ex6.main.Sjavac;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class tester {
    // TODO - change the TESTS_DIR_PATH to the absolut path to "test" dir on your pc.
    private static final String TEST_DIR_PATH = "C:\\Users\\gilza\\IdeaProjects\\Ex6\\src\\test"; //changed
    // lenovo to gilza

    public static void main(String[] args) throws Exception {

        File tests_dir = new File(TEST_DIR_PATH + "\\test_files");
        File school_sol = new File(TEST_DIR_PATH + "\\school_sol.txt");
        File output_file = new File(TEST_DIR_PATH + "\\output.txt");
        output_file.createNewFile();
        String compare = new String(Files.readAllBytes(Paths.get(school_sol.getAbsolutePath())), "UTF-8");

        File[] test_files = tests_dir.listFiles();

        PrintStream stdout = System.out;
        ByteArrayOutputStream bytes_out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bytes_out, true, "UTF-8"));

        StringBuilder output = new StringBuilder();

        for (File test : test_files) {

            try {
                Sjavac.main(new String[]{test.getAbsolutePath()});
            } catch (Exception e) {
                System.setOut(stdout);
                System.out.println(String.format("Your program threw exception in '%s', and it shouldn't", test.getName()));
                break;
            }

            output.append(test.getName()).append(" - ");
            output.append(bytes_out.toString("UTF-8"));
            bytes_out.reset();
        }

        try (FileWriter fw = new FileWriter(output_file)) {
            fw.write(String.valueOf(output));
        }

        System.setOut(stdout);

        if (!compare.contentEquals(output))
            System.out.println("Difference found in some tests, open output.txt and school_sol.txt to find the difference");
        else
            System.out.println(" ~~ ALL TESTS PASSED ~~ ");
    }


}
