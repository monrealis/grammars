package eu.vytenis.grammars.de;

import lombok.Getter;

public class Adjektiv implements Part {
	@Getter
	private final String word;

	public Adjektiv(String word) {
		this.word = word;
	}

	@Override
	public String toString() {
		return word;
	}
}
