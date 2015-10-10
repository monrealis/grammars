package eu.vytenis.grammars.de;

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
		return new Phrase(words.toArray(new Part[] {})).toString();
	}

	private void declineBestimmte() {
		if (kasus == Kasus.Nominativ)
			words.add(adjektivForm().withEnding("e"));
		else
			words.add(adjektivForm().withEnding("en"));
		Substantiv noun = (Substantiv) phrase.getWords().get(2);
		if (kasus == Kasus.Genitiv)
			words.add(new Wort(noun.toString()).withEnding("s"));
		else
			words.add(noun);
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

	private AdjektivForm adjektivForm() {
		return (AdjektivForm) phrase.getWords().get(1);
	}

}