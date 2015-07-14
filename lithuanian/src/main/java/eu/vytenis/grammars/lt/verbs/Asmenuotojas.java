package eu.vytenis.grammars.lt.verbs;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import eu.vytenis.grammars.lt.Zodis;

public abstract class Asmenuotojas {
	private final List<String> vns;
	private final List<String> dgs;

	public Asmenuotojas(String... galunes) {
		this.vns = asList(galunes).subList(0, Asmuo.values().length);
		this.dgs = asList(galunes).subList(Asmuo.values().length, galunes.length);
	}

	public List<String> getAll(Zodis z) {
		List<String> r = new ArrayList<String>();
		r.add(getVns(z, Asmuo.Pirmas));
		r.add(getVns(z, Asmuo.Antras));
		r.add(getVns(z, Asmuo.Trecias));
		r.add(getDgs(z, Asmuo.Pirmas));
		r.add(getDgs(z, Asmuo.Antras));
		r.add(getDgs(z, Asmuo.Trecias));
		return r;
	}

	public String getVns(Zodis z, Asmuo a) {
		return z.withGalune(vns.get(a.index())).toString();

	}

	public String getDgs(Zodis z, Asmuo a) {
		return z.withGalune(dgs.get(a.index())).toString();
	}
}
