package oop.ex6.main;

import oop.ex6.*;
import oop.ex6.parsers.ParserError;

import javax.imageio.IIOException;
import java.io.IOException;

public class Sjavac {

    private final static int SFILE_INDEX = 0;

    public static void main(String[] args) {
//        if (args.length != 1){
//            return;
//        }
        try {
            SReader SReader = new SReader(args[SFILE_INDEX]);
            Manager manager = new Manager(SReader);
            manager.run();
        } catch (IOException io) {
            System.err.println("IO ERROR");
        } catch (ParserError pe) {
            System.err.println(pe.getMessage());
        }

    }
}