package uo.mp.threads;

public class Incrementer implements Runnable {
	private static final int ITERATIONS = 10000;

	private Counter counter;

	public Incrementer(Counter counter) {
		this.counter = counter;
	}

	@Override
	public void run() {
		for (int i = 0; i < ITERATIONS; i++) {
			counter.increment();
		}
	}
}
