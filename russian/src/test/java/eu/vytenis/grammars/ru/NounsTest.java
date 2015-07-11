package eu.vytenis.grammars.ru;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class NounsTest {
	@Test
	public void table() {
		List<String> expected = asList("стол", "стол", "стола", "столу", "столом", "столе");
		assertEquals(expected, decline("стол"));
	}

	private List<String> decline(String word) {
		List<String> r = new ArrayList<String>();
		r.add(word + "");
		r.add(word + "");
		r.add(word + "а");
		r.add(word + "у");
		r.add(word + "ом");
		r.add(word + "");
		return r;
	}
}
