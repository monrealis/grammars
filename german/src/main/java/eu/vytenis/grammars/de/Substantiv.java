package eu.vytenis.grammars.de;

import lombok.Getter;

public class Substantiv {
	@Getter
	private final String word;

	public Substantiv(String word) {
		this.word = word;
	}

	@Override
	public String toString() {
		return word;
	}
}
