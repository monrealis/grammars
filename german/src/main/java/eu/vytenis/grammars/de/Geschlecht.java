package eu.vytenis.grammars.de;

public enum Geschlecht {
	Mannlich, Weiblich, Neutral;

	public boolean isMannlich() {
		return this == Mannlich;
	}

	public boolean isWeiblich() {
		return this == Weiblich;
	}

	public boolean isNeutral() {
		return this == Neutral;
	}
}
