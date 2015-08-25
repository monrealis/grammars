package eu.vytenis.grammars.de;

import java.util.ArrayList;
import java.util.List;

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
}
