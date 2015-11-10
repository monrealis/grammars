package eu.vytenis.grammars.de;

import static java.util.Collections.unmodifiableSet;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;

public class Substantiv implements Part {
	@Getter
	private final String substantiv;
	@Getter
	private final String ending;
	private final Set<SubstantivFeature> features = new HashSet<>();

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

	public Substantiv withFeature(Set<SubstantivFeature> features) {
		// TODO make immutable
		this.features.addAll(features);
		return this;
	}
	
	public Set<SubstantivFeature> getFeatures() {
		return unmodifiableSet(features);
	}

	@Override
	public String toString() {
		return new Wort(substantiv, ending).toString();
	}
}
