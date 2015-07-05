package eu.vytenis.grammars.lt;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class VeiksmazodziaiTest {
	@Test
	public void matyti() {
		assertEquals(getAll(parse("matyti")), asList("matau", "matai", "mato", "matome", "matote", "mato"));
	}

	private List<String> getAll(Veiksmazodis v) {
		if (v.getLinksniuote() != Linksniuote.O)
			throw new IllegalArgumentException();
		return getAllForO(v.getZodis());
	}

	private List<String> getAllForO(Zodis z) {
		List<String> r = new ArrayList<String>();
		for (String g : asList("au", "ai", "o", "ome", "ote", "o"))
			r.add(z.withGalune(g).toString());
		return r;
	}

	private Veiksmazodis parse(String s) {
		String suffix = "yti";
		if (s.endsWith(suffix)) {
			Zodis z = new Zodis(s.substring(0, s.length() - suffix.length()), s.substring(s.length() - suffix.length()));
			return new Veiksmazodis(z, Linksniuote.O);
		}
		throw new IllegalArgumentException();
	}
}
