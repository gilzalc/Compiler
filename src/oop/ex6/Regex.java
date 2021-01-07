package oop.ex6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    private static final String TRUE = "true";
    private static final String FALSE = "false";
    private static final String VALID_VARIABLE = "_\\w+|[a-zA-Z]\\w*";
    private static final String VALID_METHOD = "[a-zA-Z]\\w*";
    private static final String VALID_SUFFIX = "[{};]$";
    private static final String VALID_INTEGER = "-?\\d+";
    private static final String VALID_DOUBLE ="-?\\d+(\\.\\d+)?";
    private static final String VALID_BOOL = TRUE+"|"+FALSE+"|"+VALID_DOUBLE;
    private static final String VALID_CHAR = ".";
    private static final String PARENTHESES = "(.*)";
    private static final String SPACES = "\\s{2,}";
    private static final String SPACE_COMMENT = "^( //)";
    private static final String SPACE = "^ | $";
    private static final String EMPTY = "^\\s*$";
    private static final String COMMENT = "//.*";
    private String checkLine;

    public Regex(String line) {
        checkLine = line;
    }

    private Matcher regex(String p) {
        Pattern pattern = Pattern.compile(p);
        return pattern.matcher(checkLine);
    }

    public boolean commentOrEmpty() {
        return regex(EMPTY + "|" + COMMENT).matches();
    }

    public String checkSpaces() {
        checkLine = regex(SPACES).replaceAll(" ");
        if (regex(SPACE_COMMENT).find()){
            return checkLine;
        }
        return regex(SPACE).replaceAll("");
    }

    public boolean isValidInt(){
        return regex(VALID_INTEGER).matches();
    }

    public boolean enterScope(){
        return regex("{$").matches();
    }

//    public boolean closeScope(){
//        return regex("^}").matches();
//    }
}
