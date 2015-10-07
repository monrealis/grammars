package eu.vytenis.grammars.de;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UnbestimmteArtikelFormTest {
	@Test
	public void mannlich() {
		assertEquals("ein", toString(UnbestimmteArtikel.Ein, Kasus.Nominativ));
		assertEquals("eines", toString(UnbestimmteArtikel.Ein, Kasus.Genitiv));
		assertEquals("einem", toString(UnbestimmteArtikel.Ein, Kasus.Dativ));
		assertEquals("einen", toString(UnbestimmteArtikel.Ein, Kasus.Akkusativ));
	}

	@Test
	public void weiblich() {
		assertEquals("eine", toString(UnbestimmteArtikel.Eine, Kasus.Nominativ));
		assertEquals("einer", toString(UnbestimmteArtikel.Eine, Kasus.Genitiv));
		assertEquals("einer", toString(UnbestimmteArtikel.Eine, Kasus.Dativ));
		assertEquals("eine", toString(UnbestimmteArtikel.Eine, Kasus.Akkusativ));
	}

	@Test
	public void neutral() {
		assertEquals("ein", toString(UnbestimmteArtikel.EinN, Kasus.Nominativ));
		assertEquals("eines", toString(UnbestimmteArtikel.EinN, Kasus.Genitiv));
		assertEquals("einem", toString(UnbestimmteArtikel.EinN, Kasus.Dativ));
		assertEquals("ein", toString(UnbestimmteArtikel.EinN, Kasus.Akkusativ));
	}

	private String toString(UnbestimmteArtikel d, Kasus k) {
		return new UnbestimmteArtikelForm(d, k).toString();
	}
}
