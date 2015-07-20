package eu.vytenis.grammars.de;

import lombok.Getter;

public enum UnbestimmteArtikel implements Artikel {
	Ein(Geschlecht.Mannlich), Eine(Geschlecht.Weiblich), EinN(Geschlecht.Neutral);
	@Getter
	private final Geschlecht geschlecht;

	private UnbestimmteArtikel(Geschlecht geschlecht) {
		this.geschlecht = geschlecht;
	}
}
