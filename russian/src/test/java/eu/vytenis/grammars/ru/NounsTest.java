package eu.vytenis.grammars.ru;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class NounsTest {
	@Test
	public void table() {
		assertEquals(asList("стол", "стол", "стола", "столу", "столом", "столе"), declineSingular("стол"));
		assertEquals(asList("столы", "столы", "столов", "столам", "столами", "столах"), declinePlural("стол"));
	}

	@Test
	public void fish() {
		List<String> singular = asList("рыьа", "рыьу", "рыьы", "рыье", "рыьои", "рыье");
		assertEquals(singular, declineSingularFeminine("рыьа"));
	}

	private List<String> declineSingular(String word) {
		List<String> r = new ArrayList<String>();
		r.add(word + "");
		r.add(word + "");
		r.add(word + "а");
		r.add(word + "у");
		r.add(word + "ом");
		r.add(word + "е");
		return r;
	}

	private List<String> declinePlural(String word) {
		List<String> r = new ArrayList<String>();
		r.add(word + "ы");
		r.add(word + "ы");
		r.add(word + "ов");
		r.add(word + "ам");
		r.add(word + "ами");
		r.add(word + "ах");
		return r;
	}

	private List<String> declineSingularFeminine(String word) {
		List<String> r = new ArrayList<String>();
		String stem = word.substring(0, word.length() - 1);
		r.add(stem + "а");
		r.add(stem + "у");
		r.add(stem + "ы");
		r.add(stem + "е");
		r.add(stem + "ои");
		r.add(stem + "е");
		return r;
	}
}