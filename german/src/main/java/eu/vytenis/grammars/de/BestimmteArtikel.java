package eu.vytenis.grammars.de;

import lombok.Getter;

public enum BestimmteArtikel implements Artikel {
	Der(Geschlecht.Mannlich), Die(Geschlecht.Weiblich), Das(Geschlecht.Neutral);
	@Getter
	private Geschlecht geschlecht;

	private BestimmteArtikel(Geschlecht geschlecht) {
		this.geschlecht = geschlecht;
	}

	@Override
	public String toString() {
		return name().toLowerCase();
	}
}
