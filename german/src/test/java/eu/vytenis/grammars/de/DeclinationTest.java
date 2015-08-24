package eu.vytenis.grammars.de;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DeclinationTest {
	private static List<String> texts = new ArrayList<String>();
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
		assertEquals("der neue Mantel", new Phrase(BestimmteArtikel.Der, new Wort("neue"), new Substantiv("Mantel")).toString());
	}
}
