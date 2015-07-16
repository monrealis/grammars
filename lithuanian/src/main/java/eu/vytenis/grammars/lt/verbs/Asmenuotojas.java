package eu.vytenis.grammars.lt.verbs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import eu.vytenis.grammars.lt.Zodis;

public abstract class Asmenuotojas {
	private final Map<Skaicius, Map<Asmuo, String>> galunes = new TreeMap<Skaicius, Map<Asmuo, String>>();

	public Asmenuotojas(String... galunes) {
		Map<Asmuo, String> v = new TreeMap<Asmuo, String>();
		Map<Asmuo, String> d = new TreeMap<Asmuo, String>();
		this.galunes.put(Skaicius.Vns, v);
		this.galunes.put(Skaicius.Dgs, d);
		v.put(Asmuo.Pirmas, galunes[0]);
		v.put(Asmuo.Antras, galunes[1]);
		v.put(Asmuo.Trecias, galunes[2]);
		d.put(Asmuo.Pirmas, galunes[3]);
		d.put(Asmuo.Antras, galunes[4]);
		d.put(Asmuo.Trecias, galunes[5]);
	}

	public List<String> getAll(Zodis z) {
		List<String> r = new ArrayList<String>();
		r.add(getZodis(z, Skaicius.Vns, Asmuo.Pirmas));
		r.add(getZodis(z, Skaicius.Vns, Asmuo.Antras));
		r.add(getZodis(z, Skaicius.Vns, Asmuo.Trecias));
		r.add(getZodis(z, Skaicius.Dgs, Asmuo.Pirmas));
		r.add(getZodis(z, Skaicius.Dgs, Asmuo.Antras));
		r.add(getZodis(z, Skaicius.Dgs, Asmuo.Trecias));
		return r;
	}

	public String getZodis(Zodis z, Skaicius s, Asmuo a) {
		return z.withGalune(galunes.get(s).get(a)).toString();
	}
}
