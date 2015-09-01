package eu.vytenis.grammars.de;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
	public void get() {
		String exp = "(?<D>Der)(?<A>A)(?<S>S)";
		assertEquals("der", parser.getAsString(phrase, exp, "D"));
		assertEquals("neu", parser.getAsString(phrase, exp, "A"));
		assertEquals("Mantel", parser.getAsString(phrase, exp, "S"));
	}
}
