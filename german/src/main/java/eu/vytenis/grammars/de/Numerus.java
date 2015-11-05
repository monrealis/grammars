package eu.vytenis.grammars.de;

public enum Numerus {
	Singular, Plural;

	public boolean isSingular() {
		return this == Singular;
	}

	public boolean isPlural() {
		return this == Plural;
	}
}
