package uo.mp.dome;

import uo.mp.dome.application.Application;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
	    Application application = new Application();

	    application.retrieveDatabase();

	    // ... (run the application GUI)

	    application.updateDatabase();
	  }

}
