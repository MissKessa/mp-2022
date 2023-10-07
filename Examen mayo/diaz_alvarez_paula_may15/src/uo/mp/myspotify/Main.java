package uo.mp.myspotify;

import java.io.IOException;

import uo.mp.myspotify.service.exception.AppException;
import uo.mp.myspotify.simulator.Simulator;
import uo.mp.util.log.Logger;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		try {
		new Simulator().start();
		} catch(RuntimeException e) {
			handleArgumentValidationErrors(e);
		} catch(AppException e) {
			handleLogicErrors(e);
		}catch(IOException e) {
			handleSystemErrors(e);
		}
		
	}

	private void handleSystemErrors(IOException e) {
		System.out.println("UNRECOVERABLE ERROR Application stopped due to an internal error");
		Logger.log(e.getMessage());
		
		
	}

	private void handleLogicErrors(AppException e) {
		System.out.println(e.getMessage());
		
	}

	private void handleArgumentValidationErrors(RuntimeException e) {
		System.out.println(String.format("RECOVERABLE ERROR DUE TO %s", e.getMessage()));
		System.out.println("Please, try again");
	}


	

}
