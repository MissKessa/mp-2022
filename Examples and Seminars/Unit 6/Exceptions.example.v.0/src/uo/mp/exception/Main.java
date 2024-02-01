package uo.mp.exception;

import uo.mp.exception.util.Console;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		String fileName = Console.readString("File name");
		
		Accounter accounter = new Accounter(fileName);
		double balance = accounter.processFile();

		Console.show("The balance in your account is: " + balance + " €");
	}

}
