package eu.vytenis.grammars.de;

import lombok.Getter;

public class Wort implements Part {
	@Getter
	private final String beginning;
	@Getter
	private final String ending;

	public Wort(String beginning) {
		this(beginning, "");
	}

	public Wort(String beginning, String ending) {
		this.beginning = beginning;
		this.ending = ending;
	}

	public Wort withEnding(String ending) {
		return new Wort(beginning, ending);
	}

	@Override
	public String toString() {
		return String.format("%s%s", beginning, ending);
	}
}
