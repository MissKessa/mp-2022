package uo.mp.threads;

public class HelloRunnable implements Runnable {

	private int iterations;

	public HelloRunnable(int iterations) {
		this.iterations = iterations;
	}

	@Override
	public void run() {
		for (int i = 0; i < this.iterations; i++) {
			System.out.printf("Hello from the Runnable %d!\n", i);
		}
	}
}
