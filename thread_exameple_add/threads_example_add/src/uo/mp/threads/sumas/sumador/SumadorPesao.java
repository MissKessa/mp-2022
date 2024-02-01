package uo.mp.threads.sumas.sumador;

import uo.mp.threads.sumas.model.Suma;
import uo.mp.threads.sumas.service.AlmacenSumas;

public class SumadorPesao extends SumadorBase{

	public SumadorPesao(AlmacenSumas almacen) {
		super(almacen);
	}

	
	/**
	 * Método innecesariamente complicado a propósito
	 */
	@Override
	protected void resolverSuma(Suma suma) {
		double sumando1 = parseDouble(suma.getSumando1());

		//Valor absoluto del segundo sumando
		double sumando2abs = Math.abs(parseDouble(suma.getSumando2()));
		
		// Calculamos la cantidad a sumar a sumando1 contando. Restamos 1 a sumando2abs
		// y sumando 1 a cantidad a Sumar hasta que sumando2abs es menro que 1. 
		double cantidadASumar = 0;
		while (sumando2abs >= 1) {
			sumando2abs--;
			cantidadASumar++;
		}
		
		//Quedará una cantidad <1 que aún hay que sumar
		cantidadASumar += sumando2abs;
		
		//Si sumando 2 era negativo, la cantidad que hayamos debería restarse, no sumarse.
		if (esNumeroNegativo(suma.getSumando2())) {
			cantidadASumar *= -1;
		}
		
		//AL FIN colocamos el resultado de la suma
		suma.setResultado(sumando1 + cantidadASumar);
				
	}
	
	
	private boolean esNumeroNegativo(String number) {
		return Double.parseDouble(number) < 0;
	}
	
	
}
