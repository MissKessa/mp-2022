package uo.mp.transaction.validator.exception;

public class TransactionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransactionException() {
		// TODO Auto-generated constructor stub
	}

	public TransactionException(String message) {
		super(String.format("APPLICATION ERROR revise este error:%s", message));
	}

	public TransactionException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public TransactionException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public TransactionException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
