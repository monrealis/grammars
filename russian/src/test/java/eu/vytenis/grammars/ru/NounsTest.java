package eu.vytenis.grammars.ru;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class NounsTest {
	@Test
	public void table() {
		Word w = new Word("стол", "");
		assertEquals(asList("стол", "стол", "стола", "столу", "столом", "столе"), declineSingular(w));
		assertEquals(asList("столы", "столы", "столов", "столам", "столами", "столах"), declinePlural(w));
	}

	@Test
	public void fish() {
		List<String> singular = asList("рыба", "рыбу", "рыбы", "рыбе", "рыбои", "рыбе");
		List<String> plural = asList("рыбы", "рыбы", "рыб", "рыбам", "рыбами", "рыбах");
		Word w = new Word("рыб", "а");
		assertEquals(singular, declineSingularFeminine(w));
		assertEquals(plural.toString(), declinePluralFeminine(w).toString());
	}

	@Test
	public void wine() {
		List<String> singular = asList("вино", "вино", "вина", "вину", "вином", "вине");
		assertEquals(singular.toString(), declineSingularForNeutral(new Word("вин", "о")).toString());
	}

	private List<String> declineSingular(Word word) {
		String[] endings = { "", "", "а", "у", "ом", "е" };
		return declineWord(word, endings);
	}

	private List<String> declinePlural(Word word) {
		String[] endings = { "ы", "ы", "ов", "ам", "ами", "ах" };
		return declineWord(word, endings);
	}

	private List<String> declineSingularForNeutral(Word word) {
		String[] endings = { "о", "о", "а", "у", "ом", "е" };
		return declineWord(word, endings);
	}

	private List<String> declinePluralFeminine(Word word) {
		String[] endings = { "ы", "ы", "", "ам", "ами", "ах" };
		return declineWord(word, endings);
	}

	private List<String> declineSingularFeminine(Word word) {
		String[] endings = { "а", "у", "ы", "е", "ои", "е" };
		return declineWord(word, endings);
	}

	private List<String> declineWord(Word word, String[] endings) {
		List<String> r = new ArrayList<String>();
		for (String s : endings)
			r.add(word.withEnding(s).toString());
		return r;
	}
}
