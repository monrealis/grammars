package eu.vytenis.grammars.de;

import lombok.Getter;

public class AdjektivForm implements Part {
	@Getter
	private final Adjektiv adjektiv;
	@Getter
	private final String ending;

	public AdjektivForm(String adjektiv, String ending) {
		this(new Adjektiv(adjektiv), ending);
	}

	public AdjektivForm(Adjektiv adjektiv, String ending) {
		this.adjektiv = adjektiv;
		this.ending = ending;
	}

	public AdjektivForm withEnding(String ending) {
		return new AdjektivForm(adjektiv, ending);
	}

	@Override
	public String toString() {
		return new Wort(adjektiv.getWord(), ending).toString();
	}
}
