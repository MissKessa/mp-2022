package paypal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class is provided by PayPal to allow other developers to communicate
 * remotely with PayPal through the API methods this class provides. It is
 * locally imported in the client applications (like our payment system) and
 * does not define any other type: it is simply used by instantiating it and
 * invoking its methods with the required arguments.
 */
public class PayPal {

	/**
	 * Returned by {@code logIn} when the username or password are not valid,
	 * instead of the session token.
	 */
	public static final String INVALID_LOGIN = "invalid login";

	// valid pairs of user name and password
	private Object[][] users = { { "john@gmail.com", "@34abX!" }, { "mary@w3c.org", "dD_66%j" },
			{ "rose@uniovi.es", "yY9/aaab" } };
	private List<String> tokens = new ArrayList<>();

	// for generating the session token
	private Random random = new Random();

	/**
	 * Checks whether the user name and password provided are right (they match
	 * those of an existing PayPal user) and logins the user into the system,
	 * generating a random session token that should be used in subsequent method
	 * invocations of this class. If the login process has not been successful, it
	 * returns {@code PayPalAPI.INVALID_LOGIN} instead.
	 * 
	 * @param username the username of a PayPal user
	 * @param password the password of a PayPal user
	 * @return a string which represents a randomly generated session token, if the
	 *         user has been logged into PayPal, or {@code PayPalAPI.INVALID_LOGIN}
	 *         if the username or password were invalid
	 */
	public String logIn(String username, String password) {
		if (username == null || password == null)
			return INVALID_LOGIN;
		boolean hasLogIn = false;
		for (int i = 0; i < this.users.length; i++) {
			if (users[i][0].equals(username) && users[i][1].equals(password))
				hasLogIn = true;
		}

		if (hasLogIn) {
			String token = generateToken();
			this.tokens.add(token);
			return token;
		}
		return INVALID_LOGIN;
	}

	private String generateToken() {
		return String.valueOf(random.nextLong());
	}

	/**
	 * If the session token is valid (it belongs to one of the previously generated
	 * and saved by this class during the login process) it returns {@code true} to
	 * signal that the checkout process has taken place. Otherwise (the session
	 * token is no valid), it returns {@code false}
	 * 
	 * @param token  the previously generated session token that should have been
	 *               returned to the client during the login process
	 * @param id     a payment ID
	 * @param amount the amount of the transaction to be made
	 * @return {@code true} if the checkout could have been made; {@code true}
	 *         otherwise
	 */
	public boolean checkout(String token, String id, double amount) {
		if (token == null || id == null || amount < 0)
			return false;
		if (tokens.contains(token))
			return true;
		return false;
	}
}
