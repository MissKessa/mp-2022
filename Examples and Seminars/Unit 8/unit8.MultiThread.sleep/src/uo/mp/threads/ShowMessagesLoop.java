package uo.mp.threads;

public class ShowMessagesLoop implements Runnable {
	
	private String[] messages;

	public ShowMessagesLoop(String[] messages) {
		this.messages = messages;
	}

	@Override
	public void run() {
		try {
			
			for (int i = 0; i < messages.length; i++) {
				Thread.sleep(1000);
				Console.show( messages[i] );
			}
			
		} catch (InterruptedException e) {
			Console.show( "I have been interrupted!" );
		}
	}
}
