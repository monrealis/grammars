package eu.vytenis.grammars.de;

import java.util.ArrayList;
import java.util.List;

public class Decliner {
	private final Phrase phrase;
	private final Kasus kasus;

	public Decliner(Phrase phrase, Kasus kasus) {
		this.phrase = phrase;
		this.kasus = kasus;
	}

	public String decline() {
		List<Part> w = new ArrayList<Part>();
		if (kasus == Kasus.Nominativ)
			w.add(BestimmteArtikel.Der);
		else if (kasus == Kasus.Genitiv)
			w.add(new Wort("des"));
		AdjektivForm adj = (AdjektivForm) phrase.getWords().get(1);
		if (kasus == Kasus.Nominativ)
			w.add(adj.withEnding("e"));
		else if (kasus == Kasus.Genitiv)
			w.add(adj.withEnding("en"));
		Substantiv noun = (Substantiv) phrase.getWords().get(2);
		if (kasus == Kasus.Nominativ)
			w.add(noun);
		else if (kasus == Kasus.Genitiv)
			w.add(new Wort(noun.toString()).withEnding("s"));
		return new Phrase(w.toArray(new Part[] {})).toString();
	}
}