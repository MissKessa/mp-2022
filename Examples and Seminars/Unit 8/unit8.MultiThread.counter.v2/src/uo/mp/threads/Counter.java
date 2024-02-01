package uo.mp.threads;

/**
 * Thread safe counter
 */
public class Counter {
	private volatile int counter = 0;

	public synchronized void increment() {
		counter++;
	}

	synchronized public void decrement() {
		counter--;
	}

	public int value() {
		return counter;
	}
}
