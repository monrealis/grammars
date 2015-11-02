package eu.vytenis.grammars.de;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

public class Decliner {
	private final Kasus kasus;
	private final ExpressionParser parser;
	private final String regexp = "(?<Art>(Der|Die|Das|Ein|Eine|Einn))(?<A>A*)(?<S>S)";
	private List<Part> words = new ArrayList<Part>();

	public Decliner(Phrase phrase, Kasus kasus) {
		this.parser = new ExpressionParser(new StructureParser(phrase), regexp);
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
		else if (isBestimmte() && !isMannlich() && kasus == Kasus.Akkusativ)
			return "e";
		else if (isBestimmte())
			return "en";
		else if (!isBestimmte() && isWeiblich() && (kasus == Kasus.Nominativ || kasus == Kasus.Akkusativ))
			return "e";
		else if (!isBestimmte() && isMannlich() && kasus == Kasus.Nominativ)
			return "er";
		else if (!isBestimmte() & isNeutral() && (kasus == Kasus.Nominativ || kasus == Kasus.Akkusativ))
			return "es";
		else
			return "en";
	}

	private List<Adjektiv> changeAdjektiveEndings(String ending) {
		return adjektivForms().stream().map(a -> a.withEnding(ending)).collect(toList());
	}

	private void declineSubstantiv() {
		if (kasus == Kasus.Genitiv && !isWeiblich())
			words.add(substantivForGenitivNotWeiblich());
		else
			words.add(substantiv());
	}

	private Substantiv substantivForGenitivNotWeiblich() {
		String ending;
		if (substantiv().getSubstantiv().endsWith("d"))
			ending = "es";
		else
			ending = "s";
		return substantiv().withEnding(ending);
	}

	private boolean isBestimmte() {
		return artikel() instanceof BestimmteArtikel;
	}

	private void ensureMatches() {
		if (!parser.matches())
			throw new IllegalStateException();
	}

	private boolean isWeiblich() {
		return getGesclecht().isWeiblich();
	}

	private boolean isMannlich() {
		return getGesclecht().isMannlich();
	}
	private boolean isNeutral() {
		return getGesclecht().isNeutral();
	}

	private Geschlecht getGesclecht() {
		return artikel().getGeschlecht();
	}

	private Artikel artikel() {
		return (Artikel) parser.getOne("Art");
	}

	private List<Adjektiv> adjektivForms() {
		return parser.get("A").stream().map(a -> (Adjektiv) a).collect(toList());
	}

	private Substantiv substantiv() {
		return (Substantiv) parser.getOne("S");
	}
}