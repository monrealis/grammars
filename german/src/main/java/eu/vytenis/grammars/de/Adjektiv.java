package eu.vytenis.grammars.de;

import lombok.Getter;

public class Adjektiv implements Part {
	@Getter
	private final String adjektiv;
	@Getter
	private final String ending;

	public Adjektiv(String adjektiv) {
		this(adjektiv, "");
	}

	public Adjektiv(String adjektiv, String ending) {
		this.adjektiv = adjektiv;
		this.ending = ending;
	}

	public Adjektiv withEnding(String ending) {
		return new Adjektiv(adjektiv, ending);
	}

	@Override
	public String toString() {
		return new Wort(adjektiv, ending).toString();
	}
}
