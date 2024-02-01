package uo.mp.threads;

public class Console {
	
	public static void show(String msg) {
		String threadName = Thread.currentThread().getName();
		System.out.printf("%s: %s%n", threadName, msg);
	}

}
