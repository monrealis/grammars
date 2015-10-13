package eu.vytenis.grammars.de;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

public class Decliner {
	private final Phrase phrase;
	private final Kasus kasus;
	private final StructureParser parser;
	private final String expression = "(?<Art>(?<Der>Der)|(?<Ein>Ein))(?<A>A*)(?<S>S)";
	private List<Part> words = new ArrayList<Part>();

	public Decliner(Phrase phrase, Kasus kasus) {
		this.phrase = phrase;
		this.parser = new StructureParser(phrase);
		this.kasus = kasus;
	}

	public String decline() {
		ensureMatches();
		if (isBestimmte())
			words.add(new BestimmteArtikelForm((BestimmteArtikel) artikel(), kasus));
		declineBestimmte();
		return new Phrase(words).toString();
	}

	private void declineBestimmte() {
		if (kasus == Kasus.Nominativ)
			words.addAll(adjektivForms().stream().map(a -> a.withEnding("e")).collect(toList()));
		else
			words.addAll(adjektivForms().stream().map(a -> a.withEnding("en")).collect(toList()));
		if (kasus == Kasus.Genitiv)
			words.add(new Wort(substantiv().toString()).withEnding("s"));
		else
			words.add(substantiv());
	}

	private boolean isBestimmte() {
		return artikel() instanceof BestimmteArtikel;
	}

	private void ensureMatches() {
		if (!parser.matches(expression))
			throw new IllegalStateException();
	}

	private Artikel artikel() {
		return (Artikel) parser.getOne(expression, "Art");
	}

	private List<AdjektivForm> adjektivForms() {
		return parser.get(expression, "A").stream().map(a -> (AdjektivForm) a).collect(toList());
	}

	private Substantiv substantiv() {
		return (Substantiv) parser.getOne(expression, "S");
	}
}