package eu.vytenis.grammars.de.decl;

import static eu.vytenis.grammars.de.Kasus.Akkusativ;
import static eu.vytenis.grammars.de.Kasus.Dativ;
import static eu.vytenis.grammars.de.Kasus.Genitiv;
import static eu.vytenis.grammars.de.Kasus.Nominativ;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import eu.vytenis.grammars.de.Adjektiv;
import eu.vytenis.grammars.de.Artikel;
import eu.vytenis.grammars.de.BestimmteArtikel;
import eu.vytenis.grammars.de.BestimmteArtikelForm;
import eu.vytenis.grammars.de.ExpressionParser;
import eu.vytenis.grammars.de.Geschlecht;
import eu.vytenis.grammars.de.Kasus;
import eu.vytenis.grammars.de.Part;
import eu.vytenis.grammars.de.Phrase;
import eu.vytenis.grammars.de.StructureParser;
import eu.vytenis.grammars.de.Substantiv;
import eu.vytenis.grammars.de.UnbestimmteArtikel;
import eu.vytenis.grammars.de.UnbestimmteArtikelForm;

public class Decliner {
	private final Kasus kasus;
	private final ExpressionParser parser;
	private final String regexp = "(?<Art>(Der|Die|Das|Diepl|Ein|Eine|Einn)?)(?<A>A*)(?<S>S)";
	private List<Part> words = new ArrayList<Part>();

	public Decliner(Phrase phrase, Kasus kasus) {
		this.parser = new ExpressionParser(new StructureParser(phrase), regexp);
		this.kasus = kasus;
	}

	public String decline() {
		ensureMatches();
		if (artikel() != null)
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
		if (isPlural())
			return "en";
		if (isBestimmte() && kasus == Nominativ)
			return "e";
		else if (isBestimmte() && !isMannlich() && kasus == Akkusativ)
			return "e";
		else if (isBestimmte())
			return "en";
		else if (!isBestimmte() && isWeiblich() && (kasus == Nominativ || kasus == Akkusativ))
			return "e";
		else if (!isBestimmte() && isMannlich() && kasus == Nominativ)
			return "er";
		else if (!isBestimmte() & isNeutral() && (kasus == Nominativ || kasus == Akkusativ))
			return "es";
		else
			return "en";
	}

	private List<Adjektiv> changeAdjektiveEndings(String ending) {
		return adjektivForms().stream().map(a -> a.withEnding(ending)).collect(toList());
	}

	private void declineSubstantiv() {
		if (isPlural() && kasus == Dativ)
			words.add(substantiv().withEnding("n"));
		else if (isPlural())
			words.add(substantiv());
		else if (kasus == Genitiv && !isWeiblich())
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

	private boolean isPlural() {
		return isBestimmte() && artikel().getNumerus().isPlural();
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
		return new GeschlectFinder(artikel(), substantiv()).getGesclecht();
	}

	private Artikel artikel() {
		return (Artikel) parser.getOptionalOne("Art");
	}

	private List<Adjektiv> adjektivForms() {
		return parser.get("A").stream().map(a -> (Adjektiv) a).collect(toList());
	}

	private Substantiv substantiv() {
		return (Substantiv) parser.getOne("S");
	}
}