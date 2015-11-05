package eu.vytenis.grammars.de;

import static eu.vytenis.grammars.de.BestimmteArtikel.Das;
import static eu.vytenis.grammars.de.BestimmteArtikel.Der;
import static eu.vytenis.grammars.de.BestimmteArtikel.Die;
import static eu.vytenis.grammars.de.BestimmteArtikel.DiePl;
import static eu.vytenis.grammars.de.Kasus.Akkusativ;
import static eu.vytenis.grammars.de.Kasus.Dativ;
import static eu.vytenis.grammars.de.Kasus.Genitiv;
import static eu.vytenis.grammars.de.Kasus.Nominativ;
import static eu.vytenis.grammars.de.UnbestimmteArtikel.Ein;
import static eu.vytenis.grammars.de.UnbestimmteArtikel.EinN;
import static eu.vytenis.grammars.de.UnbestimmteArtikel.Eine;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

public class DeclinationTest {
	private static List<String> texts = new ArrayList<String>();
	private Phrase derNeueMantel = new Phrase(Der, new Adjektiv("neu", "e"), new Substantiv("Mantel"));
	private Phrase derMantel = new Phrase(Der, new Substantiv("Mantel"));
	private Phrase einMantel = new Phrase(Ein, new Substantiv("Mantel"));
	private Phrase einNeuerMantel = new Phrase(Ein, new Adjektiv("neu", "er"), new Substantiv("Mantel"));
	private Phrase dieBluse = new Phrase(Die, new Substantiv("Bluse"));
	private Phrase dieNeueBluse = new Phrase(Die, new Adjektiv("neu", "e"), new Substantiv("Bluse"));
	private Phrase eineBluse = new Phrase(Eine, new Substantiv("Bluse"));
	private Phrase eineNeueBluse = new Phrase(Eine, new Adjektiv("neu", "e"), new Substantiv("Bluse"));
	private Phrase einHemd = new Phrase(EinN, new Substantiv("Hemd"));
	private Phrase dasHemd = new Phrase(Das, new Substantiv("Hemd"));
	private Phrase dasNeueHemd = new Phrase(Das, new Adjektiv("neu", "e"), new Substantiv("Hemd"));
	private Phrase einNeuesHemd = new Phrase(EinN, new Adjektiv("neu", "es"), new Substantiv("Hemd"));
	private Phrase dieNeuenSchuhe = new Phrase(DiePl, new Adjektiv("neu", "en"), new Substantiv("Schuhe"));
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
		assertEquals("der neue Mantel", derNeueMantel.toString());
		assertEquals("ein neuer Mantel", einNeuerMantel.toString());
	}

	@Test
	public void parseStructure() {
		StructureParser p = new StructureParser(derNeueMantel);
		assertEquals("DerAS", p.parse());
		assertTrue(p.matches("Der(A*)S"));
		assertEquals("neue", p.get("Der(?<A>A*)S", "A").iterator().next().toString());
	}

	@Test
	public void declinesDenNeuenMantel() {
		assertEquals("der neue Mantel", declineDenNeuenMantel(Nominativ));
		assertEquals("des neuen Mantels", declineDenNeuenMantel(Genitiv));
		assertEquals("dem neuen Mantel", declineDenNeuenMantel(Dativ));
		assertEquals("den neuen Mantel", declineDenNeuenMantel(Akkusativ));
	}

	@Test
	public void declinesDenMantel() {
		assertEquals("der Mantel", declineDenMantel(Nominativ));
		assertEquals("des Mantels", declineDenMantel(Genitiv));
		assertEquals("dem Mantel", declineDenMantel(Dativ));
		assertEquals("den Mantel", declineDenMantel(Akkusativ));
	}

	@Test
	public void declinesEinMantel() {
		assertEquals("ein Mantel", declineEinMantel(Nominativ));
		assertEquals("eines Mantels", declineEinMantel(Genitiv));
		assertEquals("einem Mantel", declineEinMantel(Dativ));
		assertEquals("einen Mantel", declineEinMantel(Akkusativ));
	}

	@Test
	public void declinesEinenNeuenMantel() {
		assertEquals("ein neuer Mantel", declineEinNeuenMantel(Nominativ));
		assertEquals("eines neuen Mantels", declineEinNeuenMantel(Genitiv));
		assertEquals("einem neuen Mantel", declineEinNeuenMantel(Dativ));
		assertEquals("einen neuen Mantel", declineEinNeuenMantel(Akkusativ));
	}

	@Test
	public void declinesDieBluse() {
		assertEquals("die Bluse", declineDieBluse(Nominativ));
		assertEquals("der Bluse", declineDieBluse(Genitiv));
		assertEquals("der Bluse", declineDieBluse(Dativ));
		assertEquals("die Bluse", declineDieBluse(Akkusativ));
	}

	@Test
	public void declinesDieNeueBluse() {
		assertEquals("die neue Bluse", declineDieNeueBluse(Nominativ));
		assertEquals("der neuen Bluse", declineDieNeueBluse(Genitiv));
		assertEquals("der neuen Bluse", declineDieNeueBluse(Dativ));
		assertEquals("die neue Bluse", declineDieNeueBluse(Akkusativ));
	}

	@Test
	public void declinesEineBluse() {
		assertEquals("eine Bluse", declineEineBluse(Nominativ));
		assertEquals("einer Bluse", declineEineBluse(Genitiv));
		assertEquals("einer Bluse", declineEineBluse(Dativ));
		assertEquals("eine Bluse", declineEineBluse(Akkusativ));
	}

	@Test
	public void declinesEineNeueBluse() {
		assertEquals("eine neue Bluse", declineEineNeueBluse(Nominativ));
		assertEquals("einer neuen Bluse", declineEineNeueBluse(Genitiv));
		assertEquals("einer neuen Bluse", declineEineNeueBluse(Dativ));
		assertEquals("eine neue Bluse", declineEineNeueBluse(Akkusativ));
	}

	@Test
	public void declinesEinHemd() {
		assertEquals("ein Hemd", declineEinHemd(Nominativ));
		assertEquals("eines Hemdes", declineEinHemd(Genitiv));
		assertEquals("einem Hemd", declineEinHemd(Dativ));
		assertEquals("ein Hemd", declineEinHemd(Akkusativ));
	}

	@Test
	public void declinesEinNeuesHemd() {
		assertEquals("ein neues Hemd", declineEinNeuesHemd(Nominativ));
		assertEquals("eines neuen Hemdes", declineEinNeuesHemd(Genitiv));
		assertEquals("einem neuen Hemd", declineEinNeuesHemd(Dativ));
		assertEquals("ein neues Hemd", declineEinNeuesHemd(Akkusativ));
	}

	@Test
	public void declinesDasNeueHemd() {
		assertEquals("das neue Hemd", declineDasNeueHemd(Nominativ));
		assertEquals("des neuen Hemdes", declineDasNeueHemd(Genitiv));
		assertEquals("dem neuen Hemd", declineDasNeueHemd(Dativ));
		assertEquals("das neue Hemd", declineDasNeueHemd(Akkusativ));
	}

	@Test
	public void declinesDasHemd() {
		assertEquals("das Hemd", declineDasHemd(Nominativ));
		assertEquals("des Hemdes", declineDasHemd(Genitiv));
		assertEquals("dem Hemd", declineDasHemd(Dativ));
		assertEquals("das Hemd", declineDasHemd(Akkusativ));
	}

	@Test
	@Ignore
	public void declinesDieNeuenSchuhe() {
		assertEquals("die neuen Schuhe", declineDieNeuenSchuhe(Nominativ));
		assertEquals("der neuen Schuhe", declineDieNeuenSchuhe(Genitiv));
		assertEquals("den neuen Schuhen", declineDieNeuenSchuhe(Dativ));
		assertEquals("die neuen Schuhe", declineDieNeuenSchuhe(Akkusativ));
	}

	private String declineDieNeuenSchuhe(Kasus kasus) {
		return decline(dieNeuenSchuhe, kasus);
	}

	private String declineDenNeuenMantel(Kasus kasus) {
		return decline(derNeueMantel, kasus);
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

	private String declineDieNeueBluse(Kasus kasus) {
		return decline(dieNeueBluse, kasus);
	}

	private String declineEineBluse(Kasus kasus) {
		return decline(eineBluse, kasus);
	}

	private String declineEineNeueBluse(Kasus kasus) {
		return decline(eineNeueBluse, kasus);
	}

	private String declineEinHemd(Kasus kasus) {
		return decline(einHemd, kasus);
	}

	private String declineEinNeuesHemd(Kasus kasus) {
		return decline(einNeuesHemd, kasus);
	}

	private String declineDasHemd(Kasus kasus) {
		return decline(dasHemd, kasus);
	}

	private String declineDasNeueHemd(Kasus kasus) {
		return decline(dasNeueHemd, kasus);
	}

	private String decline(Phrase phrase, Kasus k) {
		return new Decliner(phrase, k).decline();
	}
}
