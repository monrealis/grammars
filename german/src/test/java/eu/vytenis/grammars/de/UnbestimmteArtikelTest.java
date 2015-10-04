package eu.vytenis.grammars.de;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UnbestimmteArtikelTest {
	@Test
	public void toString_returnsArticles() {
		assertEquals("ein", UnbestimmteArtikel.Ein.toString());
		assertEquals("eine", UnbestimmteArtikel.Eine.toString());
		assertEquals("ein", UnbestimmteArtikel.EinN.toString());
	}
}
