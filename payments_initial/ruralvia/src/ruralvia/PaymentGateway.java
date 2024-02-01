package ruralvia;

public class PaymentGateway {
	private static final int NUMBER = 0;
	private static final int MONTH = 1;
	private static final int YEAR = 2;

	private Object[][] validCreditCards = { { "1111-1111-1111-1111-1111", 12, 2018 },
			{ "2222-2222-2222-2222-2222", 12, 2018 }, { "1111-1111-1111-1111-4444", 1, 2019 },
			{ "3333-3333-3333-3333-3333", 7, 2017 } };

	public boolean isValid(Transaction transaction) {
		for (Object[] row : validCreditCards) {
			String pan = (String) row[NUMBER];
			if (pan.equals(transaction.getCreditCardNumber())) {
				return row[MONTH].equals(transaction.getMonth()) && row[YEAR].equals(transaction.getYear());
			}
		}
		return false;
	}
}
