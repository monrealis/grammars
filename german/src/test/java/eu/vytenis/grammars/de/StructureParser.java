package eu.vytenis.grammars.de;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StructureParser {
	public String parse(Phrase p) {
		List<String> s = new ArrayList<String>();
		for (Object o : p.getWords()) {
			s.add(abbreviate(o));
		}
		return String.join("", s.toArray(new String[] {}));
	}

	private String abbreviate(Object o) {
		if (o == BestimmteArtikel.Der)
			return "Der";
		if (o instanceof Adjektiv)
			return "A";
		if (o instanceof Substantiv)
			return "S";
		throw new IllegalArgumentException(o.toString());
	}

	public boolean matches(Phrase p, String regexp) {
		return matcher(p, regexp).matches();
	}

	private Matcher matcher(Phrase p, String regexp) {
		return Pattern.compile(regexp).matcher(parse(p));
	}

	public String getAsString(Phrase p, String regexp, String groupName) {
		return String.join("", getStrings(p, regexp, groupName));

	}

	private List<String> getStrings(Phrase p, String regexp, String groupName) {
		List<String> strings = new ArrayList<String>();
		for (Object o : get(p, regexp, groupName))
			strings.add(o.toString());
		return strings;
	}

	// TODO refactor
	public List<Object> get(Phrase p, String regexp, String groupName) {
		if (!matches(p, regexp))
			throw new IllegalArgumentException(regexp);
		Matcher m = matcher(p, regexp);
		m.matches();
		int s = m.start(groupName);
		int e = m.end(groupName);
		int numberOfCapitalsBefore = 0;
		int numberOfCapitalsInside = 0;
		String group = m.group();
		for (int i = 0; i < s; ++i)
			if (Character.isUpperCase(group.charAt(i)))
				numberOfCapitalsBefore++;
		for (int i = s; i < e; ++i)
			if (Character.isUpperCase(group.charAt(i)))
				numberOfCapitalsInside++;
		return new ArrayList<Object>(p.getWords().subList(numberOfCapitalsBefore, numberOfCapitalsBefore + numberOfCapitalsInside));
	}
}
