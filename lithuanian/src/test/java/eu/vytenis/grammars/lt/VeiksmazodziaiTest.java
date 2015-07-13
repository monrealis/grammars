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

import org.junit.Ignore;
import org.junit.Test;

import eu.vytenis.grammars.lt.verbs.Asmenuote;

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
	
	@Test
	@Ignore
	public void pusti() {
		assertEquals(getAll(parse("pūsti")), asList("pučiu", "puti", "pučia", "pučiame", "pučiate", "pučia"));
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
		for (String g : asList("iu", "i", "i", "ime", "ite", "i"))
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
		pabaigos.put("(?<=l)ėti", Asmenuote.I);
		pabaigos.put("(?<!l)ėti", Asmenuote.A);
		pabaigos.put("ti", Asmenuote.A);
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
