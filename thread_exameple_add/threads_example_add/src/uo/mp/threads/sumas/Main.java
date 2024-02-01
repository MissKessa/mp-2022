package uo.mp.threads.sumas;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import uo.mp.threads.sumas.model.Suma;
import uo.mp.threads.sumas.service.AlmacenSumas;
import uo.mp.threads.sumas.sumador.SumadorPesao;
import uo.mp.threads.sumas.util.CreadorSumas;

public class Main {

	private static final double MIN = -100000;
	private static final double MAX = 100000;

	private static final int NUMERO_DE_SUMAS = 100000;
	private static final int NUMERO_SUMADORES = 2;

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		Stack<Suma> sumas = CreadorSumas.stackDeSumasAleatorias(NUMERO_DE_SUMAS, MIN, MAX); // Sumas sin resolver

		AlmacenSumas almacen = new AlmacenSumas(sumas); // Almacen de sumas
		List<Thread> sumadores = construirSumadores(almacen); // Construir sumadores

		long ini = System.currentTimeMillis(); // Tiempo antes de empezar a sumar

		ejecutarSumadores(sumadores); // Arrancar sumadores
		esperarASumadores(sumadores);// Esperar a sumadores

		long time = System.currentTimeMillis() - ini; // Tiempo al terminar las sumas

		System.out.println("Fin: " + time + " milisegundos usando " + NUMERO_SUMADORES + " sumadores para "
				+ NUMERO_DE_SUMAS + " sumas");
	}

	private List<Thread> construirSumadores(AlmacenSumas almacen) {
		List<Thread> sumadores = new ArrayList<Thread>();
		for (int i = 0; i < NUMERO_SUMADORES; i++) {
			sumadores.add(new Thread(new SumadorPesao(almacen))); // ESCOGER AQUÍ TIPO DE SUMADOR
		}
		return sumadores;
	}

	private void ejecutarSumadores(List<Thread> sumadores) {
		for (int i = 0; i < NUMERO_SUMADORES; i++) {
			sumadores.get(i).start();
		}
	}

	private void esperarASumadores(List<Thread> sumadores) {
		for (int i = 0; i < NUMERO_SUMADORES; i++) {
			try {
				sumadores.get(i).join();
			} catch (InterruptedException e) {
				//
			}
		}
	}
}
