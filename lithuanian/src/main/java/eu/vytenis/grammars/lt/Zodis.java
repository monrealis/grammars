package eu.vytenis.grammars.lt;

import lombok.Getter;

public class Zodis {
	@Getter
	private final String pradzia;
	@Getter
	private final String pabaiga;

	public Zodis(String pradzia, String pabaiga) {
		this.pradzia = pradzia;
		this.pabaiga = pabaiga;
	}

	public Zodis withGalune(String galune) {
		return new Zodis(pradzia, galune);
	}

	@Override
	public String toString() {
		return pradzia + pabaiga;
	}
}
