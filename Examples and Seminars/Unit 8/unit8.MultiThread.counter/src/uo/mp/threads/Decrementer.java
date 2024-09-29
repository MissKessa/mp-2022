package uo.mp.threads;

public class Decrementer implements Runnable {
	private static final int ITERATIONS = 10000;
	
	private Counter counter;

	public Decrementer(Counter counter) {
		this.counter = counter;
	}

	@Override
	public void run() {
		for (int i = 0; i < ITERATIONS; i++) {
			counter.decrement();
		}
	}
}