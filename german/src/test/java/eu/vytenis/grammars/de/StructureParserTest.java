package eu.vytenis.grammars.de;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

public class StructureParserTest {
	private StructureParser parser = new StructureParser();
	private Phrase phrase = new Phrase(BestimmteArtikel.Der, new Adjektiv("neu"), new Substantiv("Mantel"));

	@Test
	public void parse() {
		assertEquals("DerAS", parser.parse(phrase));
	}

	@Test
	public void matchesWhole() {
		assertTrue(parser.matches(phrase, "DerAS"));
	}

	@Test
	public void doesNotMatchPartially() {
		assertFalse(parser.matches(phrase, "A"));
	}

	@Test
	public void matchesWithGroups() {
		assertTrue(parser.matches(phrase, "(Der)(A)(S)"));
	}
	
	@Test
	public void matchesWithNamedGroups() {
		assertTrue(parser.matches(phrase, "(Der)(?<A>A)(S)"));
	}
	
	@Test
	@Ignore // Not implemented
	public void get() {
		parser.get(phrase, "(Der)(?<A>A)(S)", "A");
	}
}
