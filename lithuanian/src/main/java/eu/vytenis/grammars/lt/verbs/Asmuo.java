package eu.vytenis.grammars.lt.verbs;

import static java.util.Arrays.asList;

public enum Asmuo {
	Pirmas, Antras, Trecias;

	public int index() {
		return asList(values()).indexOf(this);
	}
}
