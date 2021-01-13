package oop.ex6.parsers;

import oop.ex6.regexs.Regex;
import oop.ex6.blocks.IfWhile;
import oop.ex6.blocks.Block;

import java.util.LinkedList;

public class FileParser {

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
					Block oldParserBlock = parser.getScope();
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
					throw new SuffixException("Closed more internal scopes than opened");
				}
				parser = parser.getParentParser();
				continue;
			}
			if((line = lineRegex.validSuffix()) == null){
				throw new SuffixException("not valid line suffix");
			}
			parser.addLine(line);
		}
		if (scope > GLOBAL){
			throw new SuffixException("Some of the opened internal scopes did not close");
		}
	}
}
