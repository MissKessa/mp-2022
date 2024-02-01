package uo.mp.threads;

public class Saludador implements Runnable {

	String nombre;
	int veces;

	public Saludador(String nombre, int veces) {
		this.nombre = nombre;
		this.veces = veces;
	}

	private void saludar() {
		for (int i = 0; i < veces; i++) {
			System.out.println("Hola " + nombre + "!!");
		}
	}

	@Override
	public void run() {
		saludar();
	}

}
