package oop.ex6;

enum Type {
    INT("int"),
    DOUBLE("double"), BOOLEAN("b"), STRING("s"), CHAR("s");
    private final String myName;

    Type(String s) {
        this.myName = s;
    }
}