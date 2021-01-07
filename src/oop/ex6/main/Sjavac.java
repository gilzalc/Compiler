package oop.ex6.main;
import oop.ex6.Manager;
import oop.ex6.SReader;

public class Sjavac {
    public static void main(String[] args) {
        SReader SReader = new SReader(args[0]);
        Manager manager = new Manager(SReader);
        manager.run();
    }
}