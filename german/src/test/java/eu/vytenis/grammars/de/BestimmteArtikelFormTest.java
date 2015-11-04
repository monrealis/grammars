package eu.vytenis.grammars.de;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BestimmteArtikelFormTest {
	@Test
	public void mannlich() {
		assertEquals("der", toString(BestimmteArtikel.Der, Kasus.Nominativ));
		assertEquals("des", toString(BestimmteArtikel.Der, Kasus.Genitiv));
		assertEquals("dem", toString(BestimmteArtikel.Der, Kasus.Dativ));
		assertEquals("den", toString(BestimmteArtikel.Der, Kasus.Akkusativ));
	}

	@Test
	public void weiblich() {
		assertEquals("die", toString(BestimmteArtikel.Die, Kasus.Nominativ));
		assertEquals("der", toString(BestimmteArtikel.Die, Kasus.Genitiv));
		assertEquals("der", toString(BestimmteArtikel.Die, Kasus.Dativ));
		assertEquals("die", toString(BestimmteArtikel.Die, Kasus.Akkusativ));
	}

	@Test
	public void neutral() {
		assertEquals("das", toString(BestimmteArtikel.Das, Kasus.Nominativ));
		assertEquals("des", toString(BestimmteArtikel.Das, Kasus.Genitiv));
		assertEquals("dem", toString(BestimmteArtikel.Das, Kasus.Dativ));
		assertEquals("das", toString(BestimmteArtikel.Das, Kasus.Akkusativ));
	}
	
	@Test
	public void plural() {
		assertEquals("die", toString(BestimmteArtikel.DiePl, Kasus.Nominativ));
		assertEquals("der", toString(BestimmteArtikel.DiePl, Kasus.Genitiv));
		assertEquals("den", toString(BestimmteArtikel.DiePl, Kasus.Dativ));
		assertEquals("die", toString(BestimmteArtikel.DiePl, Kasus.Akkusativ));
	}


	private String toString(BestimmteArtikel d, Kasus k) {
		return new BestimmteArtikelForm(d, k).toString();
	}
}
