package eu.vytenis.grammars.de;

import java.util.ArrayList;
import java.util.List;

public class Decliner {
	private final Phrase phrase;
	private final Kasus kasus;
	private final StructureParser parser;
	private List<Part> words = new ArrayList<Part>();

	public Decliner(Phrase phrase, Kasus kasus) {
		this.phrase = phrase;
		this.parser = new StructureParser(phrase);
		this.kasus = kasus;
	}

	public String decline() {
		String re = "(?<Art>(?<Der>Der)|(?<Ein>Ein))(?<A>A*)(?<S>S)";
		if (!parser.matches(re))
			throw new IllegalStateException();
		Artikel art = (Artikel) parser.getOne(re, "Art");
		if (art instanceof BestimmteArtikel)
			words.add(new BestimmteArtikelForm((BestimmteArtikel) art, kasus));
		AdjektivForm adj = (AdjektivForm) phrase.getWords().get(1);
		if (kasus == Kasus.Nominativ)
			words.add(adj.withEnding("e"));
		else
			words.add(adj.withEnding("en"));
		Substantiv noun = (Substantiv) phrase.getWords().get(2);
		if (kasus == Kasus.Genitiv)
			words.add(new Wort(noun.toString()).withEnding("s"));
		else
			words.add(noun);
		return new Phrase(words.toArray(new Part[] {})).toString();
	}
}