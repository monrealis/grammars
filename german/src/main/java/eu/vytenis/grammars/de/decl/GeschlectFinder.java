package eu.vytenis.grammars.de.decl;

import eu.vytenis.grammars.de.Artikel;
import eu.vytenis.grammars.de.Geschlecht;

public class GeschlectFinder {
	private final Artikel artikel;

	public GeschlectFinder(Artikel artikel) {
		this.artikel = artikel;
	}

	public Geschlecht getGesclecht() {
		return artikel.getGeschlecht();
	}
}
