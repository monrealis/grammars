package eu.vytenis.grammars.de;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

public class Decliner {
	private final Phrase phrase;
	private final Kasus kasus;
	private final StructureParser parser;
	private final String expression = "(?<Art>(Der|Die|Ein|Eine))(?<A>A*)(?<S>S)";
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
		else
			words.add(new UnbestimmteArtikelForm((UnbestimmteArtikel) artikel(), kasus));
		declineBestimmte();
		declineSubstantiv();
		return new Phrase(words).toString();
	}

	private void declineBestimmte() {
		words.addAll(changeAdjektiveEndings(getAdjektivEnding()));
	}

	private String getAdjektivEnding() {
		if (isBestimmte() && kasus == Kasus.Nominativ)
			return "e";
		else if (isBestimmte() && isWeiblich() && kasus == Kasus.Akkusativ)
			return "e";
		else if (isBestimmte())
			return "en";
		else if (!isBestimmte() && isWeiblich() && (kasus == Kasus.Nominativ || kasus == Kasus.Akkusativ))
			return "e";
		else if (!isBestimmte() && kasus == Kasus.Nominativ)
			return "er";
		else
			return "en";
	}

	private List<Adjektiv> changeAdjektiveEndings(String ending) {
		return adjektivForms().stream().map(a -> a.withEnding(ending)).collect(toList());
	}

	private void declineSubstantiv() {
		if (kasus == Kasus.Genitiv && !isWeiblich())
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

	private boolean isWeiblich() {
		return getGesclecht().isWeiblich();
	}

	private Geschlecht getGesclecht() {
		return artikel().getGeschlecht();
	}

	private Artikel artikel() {
		return (Artikel) parser.getOne(expression, "Art");
	}

	private List<Adjektiv> adjektivForms() {
		return parser.get(expression, "A").stream().map(a -> (Adjektiv) a).collect(toList());
	}

	private Substantiv substantiv() {
		return (Substantiv) parser.getOne(expression, "S");
	}
}