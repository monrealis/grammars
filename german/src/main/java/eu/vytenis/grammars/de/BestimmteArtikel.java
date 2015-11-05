package eu.vytenis.grammars.de;

import lombok.Getter;

public enum BestimmteArtikel implements Artikel, Part {
	Der(Geschlecht.Mannlich, Numerus.Singular), //
	Die(Geschlecht.Weiblich, Numerus.Singular), //
	Das(Geschlecht.Neutral, Numerus.Singular), //
	DiePl(null, Numerus.Plural);
	@Getter
	private final Geschlecht geschlecht;
	@Getter
	private final Numerus numerus;

	private BestimmteArtikel(Geschlecht geschlecht, Numerus numerus) {
		this.geschlecht = geschlecht;
		this.numerus = numerus;
	}

	public BestimmteArtikelForm withKasus(Kasus kasus) {
		return new BestimmteArtikelForm(this, kasus);
	}

	@Override
	public String toString() {
		return name().toLowerCase();
	}
}
