package eu.vytenis.grammars.de;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DeclinationTest {
	private static List<String> texts = new ArrayList<String>();
	private Phrase mantel = new Phrase(BestimmteArtikel.Der, new Adjektiv("neu", "e"), new Substantiv("Mantel"));
	private Phrase derMantel = new Phrase(BestimmteArtikel.Der, new Substantiv("Mantel"));
	private Phrase einMantel = new Phrase(UnbestimmteArtikel.Ein, new Substantiv("Mantel"));
	private Phrase einNeuerMantel = new Phrase(UnbestimmteArtikel.Ein, new Adjektiv("neu", "er"), new Substantiv("Mantel"));
	private Phrase dieBluse = new Phrase(BestimmteArtikel.Die, new Substantiv("Bluse"));
	private Phrase dieNeueBluse = new Phrase(BestimmteArtikel.Die, new Adjektiv("neu", "e"), new Substantiv("Bluse"));
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
		assertEquals("der neue Mantel", declineMantel(Kasus.Nominativ));
		assertEquals("des neuen Mantels", declineMantel(Kasus.Genitiv));
		assertEquals("dem neuen Mantel", declineMantel(Kasus.Dativ));
		assertEquals("den neuen Mantel", declineMantel(Kasus.Akkusativ));
	}

	@Test
	public void declinesDenMantel() {
		assertEquals("der Mantel", declineDenMantel(Kasus.Nominativ));
		assertEquals("des Mantels", declineDenMantel(Kasus.Genitiv));
		assertEquals("dem Mantel", declineDenMantel(Kasus.Dativ));
		assertEquals("den Mantel", declineDenMantel(Kasus.Akkusativ));
	}

	@Test
	public void declinesEinMantel() {
		assertEquals("ein Mantel", declineEinMantel(Kasus.Nominativ));
		assertEquals("eines Mantels", declineEinMantel(Kasus.Genitiv));
		assertEquals("einem Mantel", declineEinMantel(Kasus.Dativ));
		assertEquals("einen Mantel", declineEinMantel(Kasus.Akkusativ));
	}

	@Test
	public void declinesEinenNeuenMantel() {
		assertEquals("ein neuer Mantel", declineEinNeuenMantel(Kasus.Nominativ));
		assertEquals("eines neuen Mantels", declineEinNeuenMantel(Kasus.Genitiv));
		assertEquals("einem neuen Mantel", declineEinNeuenMantel(Kasus.Dativ));
		assertEquals("einen neuen Mantel", declineEinNeuenMantel(Kasus.Akkusativ));
	}

	@Test
	public void declinesDieBluse() {
		assertEquals("die Bluse", declineDieBluse(Kasus.Nominativ));
		assertEquals("der Bluse", declineDieBluse(Kasus.Genitiv));
		assertEquals("der Bluse", declineDieBluse(Kasus.Dativ));
		assertEquals("die Bluse", declineDieBluse(Kasus.Akkusativ));
	}

	@Test
	public void declinesDieNeueBluse() {
		assertEquals("die neue Bluse", declineDieNeueBluse(Kasus.Nominativ));
		assertEquals("der neuen Bluse", declineDieNeueBluse(Kasus.Genitiv));
		assertEquals("der neuen Bluse", declineDieNeueBluse(Kasus.Dativ));
		assertEquals("die neue Bluse", declineDieNeueBluse(Kasus.Akkusativ));
	}

	private String declineMantel(Kasus kasus) {
		return decline(mantel, kasus);
	}

	private String declineEinMantel(Kasus kasus) {
		return decline(einMantel, kasus);
	}

	private String declineDenMantel(Kasus kasus) {
		return decline(derMantel, kasus);
	}

	private String declineEinNeuenMantel(Kasus kasus) {
		return decline(einNeuerMantel, kasus);
	}

	private String declineDieBluse(Kasus kasus) {
		return decline(dieBluse, kasus);
	}

	private Object declineDieNeueBluse(Kasus kasus) {
		return decline(dieNeueBluse, kasus);
	}

	private String decline(Phrase phrase, Kasus k) {
		return new Decliner(phrase, k).decline();
	}
}
