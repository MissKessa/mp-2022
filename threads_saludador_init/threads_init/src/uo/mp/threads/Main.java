package uo.mp.threads;

public class Main {
	public static void main(String[] args) {
		Thread s1 = new Thread(new Saludador("Don Pepito", 20));
		Thread s2 = new Thread(new Saludador("Don José", 20));
		s1.start();
		s2.start();

		try {
			s1.join(); // hilo principal para hasta que s1 pare
			s2.join(); // hilo principal para hasta que s2 pare
		} catch (InterruptedException e) {

		}
		System.out.println("Adiós");
	}
}
