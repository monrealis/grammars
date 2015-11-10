package eu.vytenis.grammars.de.decl;

import eu.vytenis.grammars.de.Artikel;
import eu.vytenis.grammars.de.Geschlecht;
import eu.vytenis.grammars.de.Substantiv;
import eu.vytenis.grammars.de.SubstantivFeature;

public class GeschlectFinder {
	private final Artikel artikel;
	private final Substantiv substantiv;

	public GeschlectFinder(Artikel artikel, Substantiv substantiv) {
		this.artikel = artikel;
		this.substantiv = substantiv;
	}

	public Geschlecht getGesclecht() {
		if (artikel != null)
			return artikel.getGeschlecht();
		if (substantiv.getFeatures().contains(SubstantivFeature.IsMannlich))
			return Geschlecht.Mannlich;
		throw new IllegalStateException();
	}
}
