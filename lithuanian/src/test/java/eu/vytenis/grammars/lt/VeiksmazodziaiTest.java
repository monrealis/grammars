package eu.vytenis.grammars.lt;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class VeiksmazodziaiTest {
	@Test
	public void matyti() {
		assertEquals(getAll(parse("matyti")), asList("matau", "matai", "mato", "matome", "matote", "mato"));
	}

	@Test
	public void tyleti() {
		assertEquals(getAll(parse("tylėti")), asList("tyliu", "tyli", "tyli", "tylime", "tylite", "tyli"));
	}

	@Test
	public void kelbeti() {
		assertEquals(getAll(parse("kalbėti")), asList("kalbu", "kalbi", "kalba", "kalbame", "kalbate", "kalba"));
	}

	private List<String> getAll(Veiksmazodis v) {
		if (v.getLinksniuote() == Asmenuote.O)
			return getAllForO(v.getZodis());
		if (v.getLinksniuote() == Asmenuote.A)
			return getAllForA(v.getZodis());
		if (v.getLinksniuote() == Asmenuote.I)
			return getAllForI(v.getZodis());
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

	private List<String> getAllForI(Zodis z) {
		List<String> r = new ArrayList<String>();
		for (String g : asList("liu", "li", "li", "lime", "lite", "li"))
			r.add(z.withGalune(g).toString());
		return r;
	}

	private Veiksmazodis parse(String veiksmazodis) {
		for (Entry<String, Asmenuote> e : createPabaigos().entrySet()) {
			Veiksmazodis v = parseWord(veiksmazodis, e.getKey(), e.getValue());
			if (v != null)
				return v;
		}
		throw new IllegalArgumentException();
	}

	private Map<String, Asmenuote> createPabaigos() {
		Map<String, Asmenuote> pabaigos = new LinkedHashMap<String, Asmenuote>();
		pabaigos.put("yti", Asmenuote.O);
		pabaigos.put("lėti", Asmenuote.I);
		pabaigos.put("ėti", Asmenuote.A);
		return pabaigos;
	}

	private Veiksmazodis parseWord(String v, String pabaiga, Asmenuote l) {
		String re = String.format("(.*)(%s)", pabaiga);
		Pattern p = Pattern.compile(re);
		Matcher m = p.matcher(v);
		if (!m.matches())
			return null;
		String pradzia = m.group(1);
		String pab = m.group(2);
		Zodis z = new Zodis(pradzia, pab);
		return new Veiksmazodis(z, l);
	}
}
