package ruralvia;

public interface Transaction {
	String getTransactionId();

	String getMerchantId();

	String getCreditCardNumber();

	int getMonth();

	int getYear();

	double getAmount();

}
