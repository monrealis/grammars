package eu.vytenis.grammars.de;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeFalse;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class DeclinationTest {
	private static List<String> texts = new ArrayList<String>();
	private Phrase mantel = new Phrase(BestimmteArtikel.Der, new Adjektiv("neu", "e"), new Substantiv("Mantel"));
	private Phrase derMantel = new Phrase(BestimmteArtikel.Der, new Substantiv("Mantel"));
	private Phrase einMantel = new Phrase(UnbestimmteArtikel.Ein, new Substantiv("Mantel"));
	private Phrase einNeuerMantel = new Phrase(UnbestimmteArtikel.Ein, new Adjektiv("neu", "er"), new Substantiv("Mantel"));
	static {
		texts.add("der neue Mantel");
		texts.add("des neuen Mantels");
		texts.add("dem neuen Mantel");
		texts.add("den neuen Mantel");
		texts.add("ein neuer Mantel");
		texts.add("eines neuen Mantel");
		texts.add("einem neuen Mantel");
		texts.add("einen neuen Mantel");
		texts.add("die neue Bluse");
		texts.add("der neuen Bluse");
		texts.add("der neuen Bluse");
		texts.add("die neue Bluse");
		texts.add("das neue Hemd");
		texts.add("des neuen Hemdes");
		texts.add("dem neuen Hemd");
		texts.add("das neue Hemd");
		texts.add("ein neues Hemd");
		texts.add("eines neuen Hemdes");
		texts.add("einem neuen Hemd");
		texts.add("ein neues Hemd");
		texts.add("eine neue Bluse");
		texts.add("einer neuen Bluse");
		texts.add("einer neuen Bluse");
		texts.add("eine neue Bluse");
	}

	@Test
	public void constructsSimplePhrases() {
		assertEquals("der neue Mantel", mantel.toString());
		assertEquals("ein neuer Mantel", einNeuerMantel.toString());
	}

	@Test
	public void parseStructure() {
		StructureParser p = new StructureParser(mantel);
		assertEquals("DerAS", p.parse());
		assertTrue(p.matches("Der(A*)S"));
		assertEquals("neue", p.get("Der(?<A>A*)S", "A").iterator().next().toString());
	}

	@Test
	public void declinesDenNeuenMantel() {
		assertEquals(4, declineMantel().size());
		assertEquals("der neue Mantel", declineMantel(Kasus.Nominativ));
		assertEquals("des neuen Mantels", declineMantel(Kasus.Genitiv));
		assertEquals("dem neuen Mantel", declineMantel(Kasus.Dativ));
		assertEquals("den neuen Mantel", declineMantel(Kasus.Akkusativ));
	}

	@Test
	public void declinesDenMantel() {
		assertEquals(4, declineDenMantel().size());
		assertEquals("der Mantel", declineDenMantel(Kasus.Nominativ));
		assertEquals("des Mantels", declineDenMantel(Kasus.Genitiv));
		assertEquals("dem Mantel", declineDenMantel(Kasus.Dativ));
		assertEquals("den Mantel", declineDenMantel(Kasus.Akkusativ));
	}

	@Test
	public void declinesEinMantel() {
		assertEquals(4, declineEinMantel().size());
		assertEquals("ein Mantel", declineEinMantel(Kasus.Nominativ));
		assertEquals("eines Mantels", declineEinMantel(Kasus.Genitiv));
		assertEquals("einem Mantel", declineEinMantel(Kasus.Dativ));
		assertEquals("einen Mantel", declineEinMantel(Kasus.Akkusativ));
	}

	@Test
	// Not prepared and implemented
	public void declinesEinenNeuenMantel() {
		assertEquals(4, declineEinNeuenMantel().size());
		assumeFalse(true);
		assertEquals("ein neuer Mantel", declineEinMantel(Kasus.Nominativ));
		assertEquals("eines neuen Mantels", declineEinMantel(Kasus.Genitiv));
		assertEquals("einem neuen Mantel", declineEinMantel(Kasus.Dativ));
		assertEquals("einen neuen Mantel", declineEinMantel(Kasus.Akkusativ));
	}

	private String declineMantel(Kasus kasus) {
		return declineMantel().get(kasus);
	}

	private String declineEinMantel(Kasus kasus) {
		return declineEinMantel().get(kasus);
	}

	private String declineDenMantel(Kasus kasus) {
		return declineDenMantel().get(kasus);
	}

	private Map<Kasus, String> declineDenMantel() {
		return decline(derMantel);
	}

	private Map<Kasus, String> declineEinMantel() {
		return decline(einMantel);
	}

	private Map<Kasus, String> declineMantel() {
		return decline(mantel);
	}

	private Map<Kasus, String> declineEinNeuenMantel() {
		return decline(einNeuerMantel);
	}

	private Map<Kasus, String> decline(Phrase phrase) {
		Map<Kasus, String> r = new LinkedHashMap<Kasus, String>();
		for (Kasus k : Kasus.values())
			r.put(k, decline(phrase, k));
		return r;
	}

	private String decline(Phrase phrase, Kasus k) {
		return new Decliner(phrase, k).decline();
	}
}
