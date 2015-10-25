package eu.vytenis.grammars.de;

import java.util.List;

public class ExpressionParser {
	private StructureParser parser;
	private String regexp;

	public ExpressionParser(StructureParser parser, String regexp) {
		this.parser = parser;
		this.regexp = regexp;
	}

	public boolean matches() {
		return parser.matches(regexp);
	}

	public Part getOne(String groupName) {
		return parser.getOne(regexp, groupName);
	}

	public List<Part> get(String groupName) {
		return parser.get(regexp, groupName);
	}
}