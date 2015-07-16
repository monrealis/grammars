package eu.vytenis.grammars.lt;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Ignore;
import org.junit.Test;

import eu.vytenis.grammars.lt.verbs.Asmenuote;
import eu.vytenis.grammars.lt.verbs.Asmenuotojas;
import eu.vytenis.grammars.lt.verbs.AsmenuotojasMaker;

public class VeiksmazodziaiTest {
	@Test
	public void matyti() {
		assertEquals(asList("matau", "matai", "mato", "matome", "matote", "mato"), getAll(parse("matyti")));
	}

	@Test
	public void tyleti() {
		assertEquals(asList("tyliu", "tyli", "tyli", "tylime", "tylite", "tyli"), getAll(parse("tylėti")));
	}

	@Test
	public void kelbeti() {
		assertEquals(asList("kalbu", "kalbi", "kalba", "kalbame", "kalbate", "kalba"), getAll(parse("kalbėti")));
	}

	@Test
	@Ignore
	public void pusti() {
		assertEquals(asList("pučiu", "puti", "pučia", "pučiame", "pučiate", "pučia").toString(), getAll(parse("pūsti")).toString());
	}

	private List<String> getAll(Veiksmazodis v) {
		Asmenuotojas m = v.getAsmenuote().accept(new AsmenuotojasMaker());
		return m.getAll(v.getZodis());
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
