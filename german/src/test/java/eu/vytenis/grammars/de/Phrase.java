package eu.vytenis.grammars.de;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.List;

public class Phrase {
	private List<Part> words = new ArrayList<Part>();

	public Phrase(Part... words) {
		this.words.addAll(asList(words));
	}

	@Override
	public String toString() {
		List<String> s = new ArrayList<String>();
		for (Object o : words)
			s.add(o.toString());
		return String.join(" ", s);
	}

	public List<Part> getWords() {
		return unmodifiableList(words);
	}
}