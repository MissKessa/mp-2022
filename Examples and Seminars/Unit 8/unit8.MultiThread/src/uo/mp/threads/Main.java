package uo.mp.threads;

public class Main {
    private static final int ITERATIONS = 10;

	public static void main(String[] args) {
	    
	    new HelloThread( ITERATIONS ).start();
	    new Thread( new HelloRunnable( ITERATIONS ) ).start();
	    
	    System.out.println("This is the end of the main thread.");
	}

}
