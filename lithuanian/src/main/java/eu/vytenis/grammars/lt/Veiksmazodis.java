package eu.vytenis.grammars.lt;

import eu.vytenis.grammars.lt.verbs.Asmenuote;
import lombok.Getter;

public class Veiksmazodis {
	@Getter
	private final Zodis zodis;
	@Getter
	private final Asmenuote asmenuote;

	public Veiksmazodis(Zodis zodis, Asmenuote asmenuote) {
		this.zodis = zodis;
		this.asmenuote = asmenuote;
	}
}
