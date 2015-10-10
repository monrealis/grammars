package eu.vytenis.grammars.de;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Phrase {
	private final List<Part> words;

	public Phrase(Part... words) {
		this(asList(words));
	}

	public <T extends Part> Phrase(Collection<T> words) {
		this.words = new ArrayList<>(words);
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