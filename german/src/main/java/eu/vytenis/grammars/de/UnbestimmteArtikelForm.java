package eu.vytenis.grammars.de;

import static java.util.Arrays.asList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;

public class UnbestimmteArtikelForm implements ArtikelForm, Part {
	private static Map<List<Object>, String> strings = createValues();

	private static Map<List<Object>, String> createValues() {
		Map<List<Object>, String> r = new HashMap<List<Object>, String>();
		r.put(key(UnbestimmteArtikel.Ein, Kasus.Nominativ), "ein");
		r.put(key(UnbestimmteArtikel.Ein, Kasus.Genitiv), "eines");
		r.put(key(UnbestimmteArtikel.Ein, Kasus.Dativ), "einem");
		r.put(key(UnbestimmteArtikel.Ein, Kasus.Akkusativ), "einen");
		r.put(key(UnbestimmteArtikel.Eine, Kasus.Nominativ), "eine");
		r.put(key(UnbestimmteArtikel.Eine, Kasus.Genitiv), "einer");
		r.put(key(UnbestimmteArtikel.Eine, Kasus.Dativ), "einer");
		r.put(key(UnbestimmteArtikel.Eine, Kasus.Akkusativ), "eine");
		r.put(key(UnbestimmteArtikel.EinN, Kasus.Nominativ), "ein");
		r.put(key(UnbestimmteArtikel.EinN, Kasus.Genitiv), "eines");
		r.put(key(UnbestimmteArtikel.EinN, Kasus.Dativ), "einem");
		r.put(key(UnbestimmteArtikel.EinN, Kasus.Akkusativ), "ein");
		return r;
	}

	private static List<Object> key(UnbestimmteArtikel b, Kasus k) {
		return asList((Object) b, k);
	}

	@Getter
	private final UnbestimmteArtikel artikel;
	@Getter
	private final Kasus kasus;

	public UnbestimmteArtikelForm(UnbestimmteArtikel artikel, Kasus kasus) {
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
