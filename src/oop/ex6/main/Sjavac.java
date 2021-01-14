package oop.ex6.main;

import oop.ex6.*;
import oop.ex6.parsers.GlobalParser;
import oop.ex6.blocks.Global;

import java.io.IOException;

/**
 * A class the receives a S-Java file, and determines whether it's format is legal
 */
public class Sjavac {

	private static final int LEGAL_CODE = 0;
	private static final int ILLEGAL_CODE = 1;
	private static final int IO_ERROR = 2;
	private static final int SJAVA_FILE_ARG = 0;
	private static final String IO_ERROR_MSG = "IO ERROR";

	public static void main(String[] args) {
		Global.setNull();
		GlobalParser.setNull();
		try {
			SReader SReader = new SReader(args[SJAVA_FILE_ARG]);
			Manager manager = new Manager(SReader);
			manager.run();
			System.out.println(LEGAL_CODE);
		} catch (IOException io) {
			System.err.println(IO_ERROR_MSG);
			System.out.println(IO_ERROR);
		} catch (IllegalSFile isf) {
			System.err.println(isf.getMessage());
			System.out.println(ILLEGAL_CODE);
		}
	}
}
