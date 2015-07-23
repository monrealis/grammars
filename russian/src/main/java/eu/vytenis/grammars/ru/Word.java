package eu.vytenis.grammars.ru;

import lombok.Getter;

public class Word {
	@Getter
	private final String beginning;
	@Getter
	private final String ending;

	public Word(String beginning, String ending) {
		this.beginning = beginning;
		this.ending = ending;
	}

	public Word withEnding(String ending) {
		return new Word(beginning, ending);
	}

	@Override
	public String toString() {
		return String.format("%s%s", beginning, ending);
	}
}
