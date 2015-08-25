package eu.vytenis.grammars.de;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StructureParserTest {
	private StructureParser parser = new StructureParser();

	@Test
	public void run() {
		Phrase p = new Phrase(BestimmteArtikel.Der, new Adjektiv("neu"), new Substantiv("Mantel"));
		assertEquals("DerAS", parser.parse(p));
	}
}
