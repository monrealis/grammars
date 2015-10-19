package eu.vytenis.grammars.de;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StructureParser {
	private final Phrase p;

	public StructureParser(Phrase p) {
		this.p = p;
	}

	public String parse() {
		List<String> s = new ArrayList<String>();
		for (Part o : p.getWords())
			s.add(abbreviate(o));
		return String.join("", s.toArray(new String[] {}));
	}

	private String abbreviate(Part o) {
		if (o == BestimmteArtikel.Der)
			return "Der";
		if (o == BestimmteArtikel.Die)
			return "Die";
		if (o == UnbestimmteArtikel.Ein)
			return "Ein";
		if (o instanceof Adjektiv)
			return "A";
		if (o instanceof Substantiv)
			return "S";
		throw new IllegalArgumentException(o.toString());
	}

	public boolean matches(String regexp) {
		return matcher(regexp).matches();
	}

	private Matcher matchingMatcher(String regexp) {
		Matcher m = matcher(regexp);
		if (!m.matches())
			throw new IllegalArgumentException(regexp);
		return m;
	}

	private Matcher matcher(String regexp) {
		return Pattern.compile(regexp).matcher(parse());
	}

	public String getAsString(String regexp, String groupName) {
		return String.join(" ", getStrings(regexp, groupName));
	}

	private List<String> getStrings(String regexp, String groupName) {
		List<String> strings = new ArrayList<String>();
		for (Object o : get(regexp, groupName))
			strings.add(o.toString());
		return strings;
	}

	public Part getOne(String regexp, String groupName) {
		List<Part> parts = get(regexp, groupName);
		if (parts.size() != 1)
			throw new IllegalArgumentException();
		return parts.iterator().next();
	}

	// TODO refactor
	public List<Part> get(String regexp, String groupName) {
		Matcher m = matchingMatcher(regexp);
		int s = m.start(groupName);
		int e = m.end(groupName);
		int numberOfCapitalsBefore = getNumberOfCapitalLetters(m.group(), 0, s);
		int numberOfCapitalsInside = getNumberOfCapitalLetters(m.group(), s, e);
		return new ArrayList<Part>(p.getWords().subList(numberOfCapitalsBefore, numberOfCapitalsBefore + numberOfCapitalsInside));
	}

	private int getNumberOfCapitalLetters(String group, int from, int to) {
		int r = 0;
		for (int i = from; i < to; ++i)
			if (Character.isUpperCase(group.charAt(i)))
				r++;
		return r;
	}
}
