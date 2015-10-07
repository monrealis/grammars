package eu.vytenis.grammars.de;

import static java.util.Arrays.asList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;

public class BestimmteArtikelForm implements Part, ArtikelForm {
	private static Map<List<Object>, String> strings = createValues();

	private static Map<List<Object>, String> createValues() {
		Map<List<Object>, String> r = new HashMap<List<Object>, String>();
		r.put(key(BestimmteArtikel.Der, Kasus.Nominativ), "der");
		r.put(key(BestimmteArtikel.Der, Kasus.Genitiv), "des");
		r.put(key(BestimmteArtikel.Der, Kasus.Dativ), "dem");
		r.put(key(BestimmteArtikel.Der, Kasus.Akkusativ), "den");
		r.put(key(BestimmteArtikel.Die, Kasus.Nominativ), "die");
		r.put(key(BestimmteArtikel.Die, Kasus.Genitiv), "der");
		r.put(key(BestimmteArtikel.Die, Kasus.Dativ), "der");
		r.put(key(BestimmteArtikel.Die, Kasus.Akkusativ), "die");
		r.put(key(BestimmteArtikel.Das, Kasus.Nominativ), "das");
		r.put(key(BestimmteArtikel.Das, Kasus.Genitiv), "des");
		r.put(key(BestimmteArtikel.Das, Kasus.Dativ), "dem");
		r.put(key(BestimmteArtikel.Das, Kasus.Akkusativ), "das");
		return r;
	}

	private static List<Object> key(BestimmteArtikel b, Kasus k) {
		return asList((Object) b, k);
	}

	@Getter
	private final BestimmteArtikel artikel;
	@Getter
	private final Kasus kasus;

	public BestimmteArtikelForm(BestimmteArtikel artikel, Kasus kasus) {
		this.artikel = artikel;
		this.kasus = kasus;
	}

	@Override
	public String toString() {
		if (strings.get(key(artikel, kasus)) == null)
			return "";
		return strings.get(key(artikel, kasus));
	}
}
