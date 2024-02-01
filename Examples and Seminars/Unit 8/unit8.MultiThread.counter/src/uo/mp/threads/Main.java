package uo.mp.threads;

public class Main {

	public static void main(String... args) throws InterruptedException {
		Counter counter = new Counter();

		Thread incrementer = new Thread( new Incrementer(counter) );
		Thread decrementer = new Thread( new Decrementer(counter) );

		incrementer.start();
		decrementer.start();

		incrementer.join();
		decrementer.join();

		System.out.printf("Value of counter is %d.", counter.value());
	}
	
}
