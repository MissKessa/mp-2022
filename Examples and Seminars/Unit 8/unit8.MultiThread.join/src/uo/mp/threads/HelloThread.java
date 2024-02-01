package uo.mp.threads;

public class HelloThread extends Thread {

	private int iterations;

	public HelloThread(int iterations) {
		this.iterations = iterations;
	}

	@Override
	public void run() {
		for (int i = 0; i < iterations; i++) {
			System.out.printf("Hello from the Thread %d!\n", i);
		}
	}
}
