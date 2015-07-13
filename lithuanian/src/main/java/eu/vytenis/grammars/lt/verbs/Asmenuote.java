package eu.vytenis.grammars.lt.verbs;

public enum Asmenuote {
	A {
		@Override
		public <T> T accept(AsmenuoteVisitor<T> visitor) {
			return visitor.visitA();
		}
	},
	O {
		@Override
		public <T> T accept(AsmenuoteVisitor<T> visitor) {
			return visitor.visitO();
		}
	},
	I {
		@Override
		public <T> T accept(AsmenuoteVisitor<T> visitor) {
			return visitor.visitI();
		}
	};

	public abstract <T> T accept(AsmenuoteVisitor<T> visitor);
}
