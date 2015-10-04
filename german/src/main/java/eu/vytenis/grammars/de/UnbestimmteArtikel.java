package eu.vytenis.grammars.de;

import lombok.Getter;

public enum UnbestimmteArtikel implements Artikel, Part {
	Ein(Geschlecht.Mannlich), Eine(Geschlecht.Weiblich), EinN(Geschlecht.Neutral, "ein");
	@Getter
	private final Geschlecht geschlecht;
	private final String text;

	private UnbestimmteArtikel(Geschlecht geschlecht) {
		this.geschlecht = geschlecht;
		this.text = name().toLowerCase();
	}

	private UnbestimmteArtikel(Geschlecht geschlecht, String text) {
		this.geschlecht = geschlecht;
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}
