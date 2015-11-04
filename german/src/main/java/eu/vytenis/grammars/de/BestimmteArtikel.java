package eu.vytenis.grammars.de;

import lombok.Getter;

public enum BestimmteArtikel implements Artikel, Part {
	Der(Geschlecht.Mannlich), Die(Geschlecht.Weiblich), Das(Geschlecht.Neutral), DiePl(null);
	@Getter
	private Geschlecht geschlecht;

	private BestimmteArtikel(Geschlecht geschlecht) {
		this.geschlecht = geschlecht;
	}

	public BestimmteArtikelForm withKasus(Kasus kasus) {
		return new BestimmteArtikelForm(this, kasus);
	}

	@Override
	public String toString() {
		return name().toLowerCase();
	}
}
