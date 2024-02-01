package uo.mp.threads.sumas.util;

import java.util.Random;
import java.util.Stack;

import uo.mp.threads.sumas.model.Suma;

public class CreadorSumas {
	
	private static final Random rand = new Random();
	
	
	public static Suma sumaAleatoria(double sumandoMinimo, double sumandoMaximo) {
		double sumando1 = sumandoAleatorio(sumandoMinimo, sumandoMaximo);
		double sumando2 = sumandoAleatorio(sumandoMinimo, sumandoMaximo);
		return new Suma(""+sumando1, ""+sumando2);
		
	}
	
	public static Stack<Suma> stackDeSumasAleatorias(double cantidad, double sumandoMinimo, double sumandoMaximo){
		Stack<Suma> res = new Stack<Suma>();
		for (int i = 0; i< cantidad; i++) {
			res.push(sumaAleatoria(sumandoMinimo, sumandoMaximo));
		}
		return res;
	}
	
	private static double sumandoAleatorio(double min, double max) {
		return   min + (max - min) * rand.nextDouble();
	}

}
