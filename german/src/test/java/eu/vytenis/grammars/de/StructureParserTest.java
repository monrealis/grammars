package eu.vytenis.grammars.de;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StructureParserTest {
	private StructureParser parser = new StructureParser();
	private Phrase phrase = new Phrase(BestimmteArtikel.Der, new Adjektiv("neu"), new Substantiv("Mantel"));
	private Phrase phrase2Adj = new Phrase(BestimmteArtikel.Der, new Adjektiv("neu"), new Adjektiv("rot"), new Substantiv("Mantel"));

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
	public void getsNamedGroups() {
		String regexp = "(?<D>Der)(?<A>A)(?<S>S)";
		assertEquals("der", getAsString(regexp, "D"));
		assertEquals("neu", getAsString(regexp, "A"));
		assertEquals("Mantel", getAsString(regexp, "S"));
	}

	@Test
	public void getsTwoAdjectives() {
		phrase = phrase2Adj;
		assertEquals("neu rot", getAsString("(?<D>Der)(?<A>A+)(?<S>S)", "A"));
	}

	private String getAsString(String regexp, String groupName) {
		return parser.getAsString(phrase, regexp, groupName);
	}
}
