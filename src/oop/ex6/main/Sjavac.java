package oop.ex6.main;

import oop.ex6.*;
import oop.ex6.parsers.ParserError;
import java.io.IOException;

public class Sjavac {

    private final static int LEGAL_CODE = 0;
    private final static int ILLEGAL_CODE = 1;
    private final static int IO_ERROR = 2;
    private final static int SFILE_ARG = 0;

    public static void main(String[] args) {
//        if (args.length != 1){
//            return;
//        }
        try {
            SReader SReader = new SReader(args[SFILE_ARG]);
            Manager manager = new Manager(SReader);
            manager.run();
        } catch (IOException io) {
            System.err.println("IO ERROR");
            System.out.println(IO_ERROR);
        } catch (IllegalSFile isf) {
            System.err.println(isf.getMessage());
            System.out.println(ILLEGAL_CODE);
        }
        System.out.println(LEGAL_CODE);

    }
}