package eu.vytenis.grammars.de;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PhraseTest {
	@Test
	public void toString_returnsString() {
		assertEquals("der Tisch", derTisch().toString());
	}

	private Phrase derTisch() {
		return new Phrase(BestimmteArtikel.Der, new Substantiv("Tisch"));
	}
}
