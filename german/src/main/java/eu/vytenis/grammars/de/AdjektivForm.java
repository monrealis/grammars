package eu.vytenis.grammars.de;

import lombok.Getter;

public class AdjektivForm implements Part {
	@Getter
	private final String adjektiv;
	@Getter
	private final String ending;

	public AdjektivForm(String adjektiv) {
		this(adjektiv, "");
	}

	public AdjektivForm(String adjektiv, String ending) {
		this.adjektiv = adjektiv;
		this.ending = ending;
	}

	public AdjektivForm withEnding(String ending) {
		return new AdjektivForm(adjektiv, ending);
	}

	@Override
	public String toString() {
		return new Wort(adjektiv, ending).toString();
	}
}
