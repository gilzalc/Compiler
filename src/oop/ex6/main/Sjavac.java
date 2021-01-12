package oop.ex6.main;

import oop.ex6.*;

public class Sjavac {

    private final static int SFILE_INDEX = 0;

    public static void main(String[] args) {
        if (args.length != 1){
            return;
        }
        SReader SReader = new SReader(args[SFILE_INDEX]);
        Manager manager = new Manager(SReader);
        manager.run();
    }
}