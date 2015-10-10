package eu.vytenis.grammars.de;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

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
		return String.join(" ", words.stream().map(w -> w.toString()).collect(toList()));
	}

	public List<Part> getWords() {
		return unmodifiableList(words);
	}
}