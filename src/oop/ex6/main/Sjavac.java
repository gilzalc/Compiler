package oop.ex6.main;

import oop.ex6.*;

import java.io.IOException;

public class Sjavac {

	private static final int LEGAL_CODE = 0;
	private static final int ILLEGAL_CODE = 1;
	private static final int IO_ERROR = 2;
	private static final int SFILE_ARG = 0;

	public static void main(String[] args) {
		//        if (args.length != 1){
		//            return;
		//        }
		try {
			SReader SReader = new SReader(args[SFILE_ARG]);
			Manager manager = new Manager(SReader);
			manager.run();
            System.out.println(LEGAL_CODE);
        } catch (IOException io) {
            System.err.println("IO ERROR");
            System.out.println(IO_ERROR);
        } catch (IllegalSFile isf) {
            System.err.println(isf.getMessage());
            System.out.println(ILLEGAL_CODE);
        }
		}
	}
