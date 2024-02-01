package uo.mp.piped;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class PipedStream {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException, InterruptedException {

		PipedWriter out = new PipedWriter();
		PipedReader in = new PipedReader();

		out.connect(in);

		Thread writer = new Thread() {

			@Override
			public void run() {
				try {
					for (int i = 0; i < 5; i++) {
						out.write('A' + i);
						sleep(1000);
					}
					sleep(1000);
					out.close();
					
				} catch (IOException | InterruptedException e) {
					throw new RuntimeException(e);
				}

			}
		};
		
		Thread reader = new Thread() {
			@Override
			public void run() {
				try {
					int ch;
					int i = 0;
					while ( i < 10 ) {
						ch = in.read();
						System.out.println( (char) ch );
						i++;
					}
					in.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}

			}			
		};
		
		Thread scanner = new Thread() {
			@Override
			public void run() {
				try {
					while ( true ) {
						System.out.println( "Available* " + in.ready() );
						sleep(200);
					}		
				} catch (IOException | InterruptedException e) {
					throw new RuntimeException(e);
				}

			}			
		};

		scanner.start();
		writer.start();
		reader.start();
		
		reader.join();
		writer.join();
		scanner.stop();
		

	}

}
