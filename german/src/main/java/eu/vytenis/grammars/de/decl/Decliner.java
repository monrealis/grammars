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
		declineArtikel();
		declineAdjektive();
		declineSubstantiv();
		return new Phrase(words).toString();
	}

	private void declineArtikel() {
		if (isBestimmte())
			words.add(new BestimmteArtikelForm((BestimmteArtikel) artikel(), kasus));
		else if (isUnbestimmte())
			words.add(new UnbestimmteArtikelForm((UnbestimmteArtikel) artikel(), kasus));
	}

	private void declineAdjektive() {
		words.addAll(changeAdjektiveEndings(getAdjektivEnding()));
	}

	private String getAdjektivEnding() {
		if (isPlural())
			return "en";
		if (isNoArtikel() && isKasus(Dativ))
			return "em";
		if (isNoArtikel() && isKasus(Akkusativ))
			return "en";
		if (isBestimmte() && isKasus(Nominativ))
			return "e";
		if (isBestimmte() && !isMannlich() && isKasus(Akkusativ))
			return "e";
		if (isBestimmte())
			return "en";
		if (isUnbestimmte() && isWeiblich() && isKasus(Nominativ, Akkusativ))
			return "e";
		if (!isBestimmte() && isMannlich() && isKasus(Nominativ))
			return "er";
		if (isUnbestimmte() & isNeutral() && isKasus(Nominativ, Akkusativ))
			return "es";
		return "en";
	}

	private boolean isKasus(Kasus... kasus) {
		for (Kasus k : kasus)
			if (this.kasus == k)
				return true;
		return false;
	}

	private List<Adjektiv> changeAdjektiveEndings(String ending) {
		return adjektivForms().stream().map(a -> a.withEnding(ending)).collect(toList());
	}

	private void declineSubstantiv() {
		words.add(substantiv().withEnding(substantivEnding()));
	}

	private String substantivEnding() {
		if (isPlural() && isKasus(Dativ))
			return "n";
		else if (isKasus(Genitiv) && isSingular() && !isWeiblich())
			return substantivGenitivEndingS();
		return "";
	}

	private String substantivGenitivEndingS() {
		String s = substantiv().getSubstantiv();
		for (String ending : new String[] { "d", "n" })
			if (s.endsWith(ending))
				return "es";
		return "s";
	}

	private boolean isSingular() {
		return !isPlural();
	}

	private boolean isPlural() {
		return isBestimmte() && artikel().getNumerus().isPlural();
	}

	private boolean isNoArtikel() {
		return artikel() == null;
	}

	private boolean isBestimmte() {
		return artikel() instanceof BestimmteArtikel;
	}

	private boolean isUnbestimmte() {
		return artikel() instanceof UnbestimmteArtikel;
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