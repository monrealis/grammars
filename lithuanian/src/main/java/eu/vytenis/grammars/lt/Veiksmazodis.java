package eu.vytenis.grammars.lt;

import lombok.Getter;

public class Veiksmazodis {
	@Getter
	private final Zodis zodis;
	@Getter
	private final Linksniuote linksniuote;

	public Veiksmazodis(Zodis zodis, Linksniuote linksniuote) {
		this.zodis = zodis;
		this.linksniuote = linksniuote;
	}

}
