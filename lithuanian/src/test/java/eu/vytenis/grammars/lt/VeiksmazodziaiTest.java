package eu.vytenis.grammars.lt;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

public class VeiksmazodziaiTest {
	@Test
	public void matyti() {
		assertEquals(getAll(parse("matyti")), asList("matau", "matai", "mato", "matome", "matote", "mato"));
	}

	@Test
	public void kelbeti() {
		assertEquals(getAll(parse("kalbėti")), asList("kalbu", "kalbi", "kalba", "kalbame", "kalbate", "kalba"));
	}

	private List<String> getAll(Veiksmazodis v) {
		if (v.getLinksniuote() == Linksniuote.O)
			return getAllForO(v.getZodis());
		if (v.getLinksniuote() == Linksniuote.A)
			return getAllForA(v.getZodis());
		throw new IllegalArgumentException();

	}

	private List<String> getAllForO(Zodis z) {
		List<String> r = new ArrayList<String>();
		for (String g : asList("au", "ai", "o", "ome", "ote", "o"))
			r.add(z.withGalune(g).toString());
		return r;
	}

	private List<String> getAllForA(Zodis z) {
		List<String> r = new ArrayList<String>();
		for (String g : asList("u", "i", "a", "ame", "ate", "a"))
			r.add(z.withGalune(g).toString());
		return r;
	}

	private Veiksmazodis parse(String veiksmazodis) {
		for (Entry<String, Linksniuote> e : createPabaigos().entrySet()) {
			Veiksmazodis v = parseWord(veiksmazodis, e.getKey(), e.getValue());
			if (v != null)
				return v;
		}
		throw new IllegalArgumentException();
	}

	private Map<String, Linksniuote> createPabaigos() {
		Map<String, Linksniuote> pabaigos = new LinkedHashMap<String, Linksniuote>();
		pabaigos.put("yti", Linksniuote.O);
		pabaigos.put("ėti", Linksniuote.A);
		return pabaigos;
	}

	private Veiksmazodis parseWord(String v, String pabaiga, Linksniuote l) {
		if (!v.endsWith(pabaiga))
			return null;
		String pradzia = v.substring(0, v.length() - pabaiga.length());
		Zodis z = new Zodis(pradzia, pabaiga);
		return new Veiksmazodis(z, l);
	}
}
