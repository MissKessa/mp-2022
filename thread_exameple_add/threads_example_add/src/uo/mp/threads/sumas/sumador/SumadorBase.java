package uo.mp.threads.sumas.sumador;

import uo.mp.threads.sumas.model.Suma;
import uo.mp.threads.sumas.service.AlmacenSumas;

public abstract class SumadorBase implements Runnable {

	private AlmacenSumas almacen;

	public SumadorBase(AlmacenSumas almacen) {
		this.almacen = almacen;
	}

	@Override
	public void run() {
		Suma suma = null;

		do {
			suma = almacen.cogerSumaDeLaPila();
			if (suma != null) {
				resolverSuma(suma);
				almacen.almacenarSumaResuelta(suma);
			}
		} while (suma != null);

	}

	protected abstract void resolverSuma(Suma suma) ;

	protected double parseDouble(String candidato) {
		return Double.parseDouble(candidato);
	}
}
