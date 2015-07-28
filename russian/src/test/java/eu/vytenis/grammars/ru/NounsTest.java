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
		List<String> singular = asList("стол", "стол", "стола", "столу", "столом", "столе");
		List<String> plural = asList("столы", "столы", "столов", "столам", "столами", "столах");
		assertEquals(singular, declineSingular(w));
		assertEquals(plural, declinePlural(w));
	}

	@Test
	public void fish() {
		Word w = new Word("рыб", "а");
		List<String> singular = asList("рыба", "рыбу", "рыбы", "рыбе", "рыбои", "рыбе");
		List<String> plural = asList("рыбы", "рыбы", "рыб", "рыбам", "рыбами", "рыбах");
		assertEquals(singular, declineSingularFeminine(w));
		assertEquals(plural, declinePluralFeminine(w));
	}

	@Test
	public void wine() {
		Word w = new Word("вин", "о");
		List<String> singular = asList("вино", "вино", "вина", "вину", "вином", "вине");
		List<String> plural = asList("вина", "вина", "вин", "винам", "винами", "винах");
		assertEquals(singular.toString(), declineSingularForNeutral(w).toString());
		assertEquals(plural.toString(), declinePluralForNeutral(w).toString());
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

	private List<String> declinePluralForNeutral(Word word) {
		String[] endings = { "а", "а", "", "ам", "ами", "ах" };
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
