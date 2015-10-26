package eu.vytenis.grammars.de;

import lombok.Getter;

public class Substantiv implements Part {
	@Getter
	private final String substantiv;
	@Getter
	private final String ending;

	public Substantiv(String substantiv) {
		this(substantiv, "");
	}

	public Substantiv(String substantiv, String ending) {
		this.substantiv = substantiv;
		this.ending = ending;
	}

	public Substantiv withEnding(String ending) {
		return new Substantiv(substantiv, ending);
	}

	@Override
	public String toString() {
		return new Wort(substantiv, ending).toString();
	}
}
