package eu.vytenis.grammars.ru;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class NounsTest {
	@Test
	public void table() {
		assertEquals(asList("стол", "стол", "стола", "столу", "столом", "столе"), decline("стол"));
		assertEquals(asList("столы", "столы", "столов", "столам", "столами", "столах"), declinePlural("стол"));
	}

	private List<String> decline(String word) {
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
}
