package uo.mp.lab11.marker.parser;

import uo.mp.util.check.ArgumentChecks;

public class InvalidLineFormatException extends Exception {

	private static final long serialVersionUID = -2562541870850788940L;
	private int lineNumber;

	public InvalidLineFormatException(int lineNumber, String message) {
		super(message);
		ArgumentChecks.isTrue(lineNumber > 0);
		this.lineNumber = lineNumber;
	}

	public InvalidLineFormatException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		if (lineNumber > 0)
			return String.format("INVALID LINE [%s] : %s", this.lineNumber, super.getMessage());
		return String.format("INVALID LINE : %s", super.getMessage());
	}

}
