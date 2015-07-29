package eu.vytenis.grammars.ru;

import lombok.Getter;

public class Noun {
	@Getter
	private final Word word;
	@Getter
	private final Gender gender;

	public Noun(Word word, Gender gender) {
		this.word = word;
		this.gender = gender;
	}
}
