package oop.ex6.main;
import oop.ex6.Manager;
import oop.ex6.Reader;

public class Sjavac {
    public static void main(String[] args) {
        Reader reader = new Reader(args[0]);
        Manager manager = new Manager(reader);
        manager.run();
    }
}