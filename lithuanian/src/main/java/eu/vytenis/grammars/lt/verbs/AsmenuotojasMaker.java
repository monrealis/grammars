package eu.vytenis.grammars.lt.verbs;

public class AsmenuotojasMaker implements AsmenuoteVisitor<Asmenuotojas> {
	public Asmenuotojas visitA() {
		return new AsmenuotojasA();
	}

	public Asmenuotojas visitI() {
		return new AsmenuotojasI();
	}

	public Asmenuotojas visitO() {
		return new AsmenuotojasO();
	}
}
