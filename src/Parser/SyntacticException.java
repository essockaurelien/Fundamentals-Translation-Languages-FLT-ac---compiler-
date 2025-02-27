package Parser;

public class SyntacticException extends Exception {

	private static final long serialVersionUID = 2L;

	public SyntacticException(String riga) {

		super("Scanner: SyntacticException, row: " + riga);

	}
}
