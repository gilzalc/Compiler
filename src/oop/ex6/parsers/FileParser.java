package oop.ex6.parsers;

import oop.ex6.regexs.Regex;
import oop.ex6.blocks.IfWhile;
import oop.ex6.blocks.Block;

import java.util.LinkedList;

/**
 * A class that parses all of the lines of the file, and divides them into blocks while checking for basic
 * requirements of the format
 */
public class FileParser {
	private static final  String NOT_VALID_SUFFIX_MSG ="not valid line suffix";
	private static final  String TOO_MANY_CLOSING_BRACKETS_MSG ="Closed more internal scopes than opened";
	private static final  String TOO_FEW_SUFFIX_MSG ="Some of the opened internal scopes did not close";
	private final static int GLOBAL = 0;
	private final static int METHOD = 1;
	private final static int IF_WHILE = 2;
	private int scope = 0;
	private final LinkedList<String> fixedLines;
	private Parser parser;

	public FileParser(LinkedList<String> lines) {
		fixedLines = lines;
		parser = GlobalParser.getInstance();
	}

	/**
	 * only method - responsible for creating the parsers and giving each one its scope lines
	 * @throws ParserException in case of invalid format of scopes, or a line that doesn't end with a valid
	 * suffix
	 */
	public void run() throws ParserException {
		for (String line : fixedLines) {
			Regex lineRegex = new Regex(line);
			if (lineRegex.enterScope()) { //check for "void"?
				scope++;
				if (scope == METHOD) {
					parser = new MethodParser(parser);
				}
				if (scope >= IF_WHILE){
					parser.addLine("{");
					Block oldParserBlock = parser.getBlock();
					IfWhile ifWhileScope = new IfWhile(oldParserBlock);
					parser = new IfWhileParser(parser, ifWhileScope);
				}
				parser.getParentParser().addChildParsers(parser);
				parser.addLine(line);
				continue;
			}
			if (line.equals("}")) {
				scope--;
				if (scope < GLOBAL) {
					throw new SuffixException(TOO_MANY_CLOSING_BRACKETS_MSG);
				}
				parser = parser.getParentParser();
				continue;
			}
			if((line = lineRegex.validSuffix()) == null){
				throw new SuffixException(NOT_VALID_SUFFIX_MSG);
			}
			parser.addLine(line);
		}
		if (scope > GLOBAL){
			throw new SuffixException(TOO_FEW_SUFFIX_MSG);
		}
	}
}
