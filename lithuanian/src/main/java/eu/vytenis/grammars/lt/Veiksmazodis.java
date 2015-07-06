package eu.vytenis.grammars.lt;

import lombok.Getter;

public class Veiksmazodis {
	@Getter
	private final Zodis zodis;
	@Getter
	private final Asmenuote linksniuote;

	public Veiksmazodis(Zodis zodis, Asmenuote linksniuote) {
		this.zodis = zodis;
		this.linksniuote = linksniuote;
	}

}
