package uo.mp.threads;

public class Main {

	public static void main(String args[]) throws InterruptedException {

		String[] messages = new String[] {
				"Linear Algebra", 
				"Calculus", 
				"Business", 
				"Computing Basics",
				"Introduction to Programming" 
			};
		long patience = 3000; // by default, 3 seconds
		
		Console.show("Starting MessageLoop thread.");

		Thread t = new Thread( new ShowMessagesLoop( messages ) );

		t.start();
		
		Console.show("Waiting for MessageLoop thread to finish.");
		Thread.sleep(patience);
		
		Console.show("Tired of waiting!");
		t.interrupt();
		t.join();
		
		Console.show("End of execution.");
	}
}
