package uo.mp2122.newsstand.exception;

public class NewsstandException extends Exception {

	private static final long serialVersionUID = -2562541870850788940L;

	public NewsstandException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		return String.format("Error: %s", super.getMessage());
	}
}
