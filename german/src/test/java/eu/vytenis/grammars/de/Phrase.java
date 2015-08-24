package eu.vytenis.grammars.de;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

public class Phrase {
	private List<Object> words = new ArrayList<Object>();
	
	public Phrase(Object... words) {
		this.words.addAll(asList(words));
	}
			
	@Override
	public String toString() {
		List<String> s = new ArrayList<String>();
		for (Object o : words)
			s.add(o.toString());
		return String.join(" ", s);
	}
}