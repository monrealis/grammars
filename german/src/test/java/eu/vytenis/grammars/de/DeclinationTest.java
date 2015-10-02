package eu.vytenis.grammars.de;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class DeclinationTest {
	private static List<String> texts = new ArrayList<String>();
	private Phrase mantel = new Phrase(BestimmteArtikel.Der, new AdjektivForm("neu", "e"), new Substantiv("Mantel"));
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
	public void constructsSimplePhrase() {
		assertEquals("der neue Mantel", mantel.toString()); // Neteisingas
	}

	@Test
	public void parseStructure() {
		StructureParser p = new StructureParser();
		assertEquals("DerAS", p.parse(mantel));
		assertTrue(p.matches(mantel, "Der(A*)S"));
		assertEquals("neue", p.get(mantel, "Der(?<A>A*)S", "A").iterator().next().toString());
	}

	@Test
	public void declinesDenNeuenMantel() {
		assertEquals(4, declineMantel().size());
		assertEquals("der neue Mantel", declineMantel(Kasus.Nominativ));
		assertEquals("des neuen Mantels", declineMantel(Kasus.Genitiv));
		assertEquals("dem neuen Mantel", declineMantel(Kasus.Dativ));
		assertEquals("den neuen Mantel", declineMantel(Kasus.Akkusativ));
	}

	private String declineMantel(Kasus kasus) {
		return declineMantel().get(kasus);
	}

	private Map<Kasus, String> declineMantel() {
		Map<Kasus, String> r = new LinkedHashMap<Kasus, String>();
		for (Kasus k : Kasus.values())
			r.put(k, declineBestimmte(mantel, k));
		return r;
	}

	private String declineBestimmte(Phrase phrase, Kasus k) {
		return new Decliner(phrase, k).decline();
	}
}
