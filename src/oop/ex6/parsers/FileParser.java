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
	private static final String NOT_VALID_SUFFIX_MSG = "not valid line suffix";
	private static final String TOO_MANY_CLOSING_BRACKETS_MSG = "Closed more internal scopes than opened";
	private static final String TOO_FEW_SUFFIX_MSG = "Some of the opened internal scopes did not close";
	private final static int GLOBAL_DEPTH = 0;
	private final static int METHOD_DEPTH = 1;
	private final static int IF_WHILE_DEPTH = 2;
	private static final String EXIT_BLOCK = "}";
	private static final String ENTER_BLOCK = "{";
	private int depth = 0;
	private final LinkedList<String> fixedLines;
	private Parser parser;

	public FileParser(LinkedList<String> lines) {
		fixedLines = lines;
		parser = GlobalParser.getInstance();
	}

	/**
	 * only method - responsible for creating the parsers and giving each one its scope lines
	 * @throws ParserException in case of invalid format of scopes, or a line that doesn't end with a valid
	 * 		suffix
	 */
	public void run() throws ParserException {
		for (String line : fixedLines) {
			Regex lineRegex = new Regex(line);
			if (lineRegex.enterBlock()) { //check for "void"?
				depth++;
				if (depth == METHOD_DEPTH) {
					parser = new MethodParser(parser);
				}
				if (depth >= IF_WHILE_DEPTH) {
					parser.addLine(ENTER_BLOCK);
					Block oldParserBlock = parser.getBlock();
					IfWhile ifWhileBlock = new IfWhile(oldParserBlock);
					parser = new IfWhileParser(parser, ifWhileBlock);
				}
				parser.getParentParser().addChildParsers(parser);
				parser.addLine(line);
				continue;
			}
			if (line.equals(EXIT_BLOCK)) {
				depth--;
				if (depth < GLOBAL_DEPTH) {
					throw new SuffixException(TOO_MANY_CLOSING_BRACKETS_MSG);
				}
				parser = parser.getParentParser();
				continue;
			}
			if ((line = lineRegex.validSuffix()) == null) {
				throw new SuffixException(NOT_VALID_SUFFIX_MSG);
			}
			parser.addLine(line);
		}
		if (depth > GLOBAL_DEPTH) {
			throw new SuffixException(TOO_FEW_SUFFIX_MSG);
		}
	}
}
