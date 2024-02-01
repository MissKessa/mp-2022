package uo.mp.threads.sumas.service;

import java.util.Stack;

import uo.mp.threads.sumas.model.Suma;

public class AlmacenSumas {
	
	private Stack<Suma> noResueltas; // vació
	private Stack<Suma> resueltas; // lleno
	
	public AlmacenSumas(Stack<Suma> noResueltas ) {
		this.noResueltas = noResueltas;
		this.resueltas = new Stack<Suma>();
	}
	
	public synchronized Suma cogerSumaDeLaPila() {
		if( ! noResueltas.isEmpty()) {
			//
			return noResueltas.pop();
		}
		return null;
	}
	
	
	public synchronized void almacenarSumaResuelta(Suma suma) {
		resueltas.push(suma);	
	}
	

}
