package uo.mp2122.newsstand.service.parsers;

import uo.mp.util.check.ArgumentChecks;

public class IllegalLineFormatException extends Exception {

	private static final long serialVersionUID = -2562541870850788940L;
	private int lineNumber;

	public IllegalLineFormatException(int lineNumber, String message) {
		super(message);
		ArgumentChecks.isTrue(lineNumber > 0);
		this.lineNumber = lineNumber;
	}

	@Override
	public String getMessage() {
		return String.format("INVALID LINE [%s] : %s", this.lineNumber, super.getMessage());
	}
}
