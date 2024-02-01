package uo.mp.threads;

public class Main {
    private static final int ITERATIONS = 10;

	public static void main(String[] args) throws InterruptedException {
	    
	    Thread t1 = new HelloThread( ITERATIONS );
	    Thread t2 = new Thread( new HelloRunnable( ITERATIONS ) );

	    t1.start();
	    t2.start();
	    
		t1.join();
	    t2.join();
	    
	    System.out.println("This is the end of the main thread.");
	}

}
