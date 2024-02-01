package uo.mp.threads.sumas.sumador;

import uo.mp.threads.sumas.model.Suma;
import uo.mp.threads.sumas.service.AlmacenSumas;

public class SumadorSuperListo extends SumadorBase{
	
	
	public SumadorSuperListo(AlmacenSumas almacen) {
		super(almacen);
	}

	@Override
	protected void resolverSuma(Suma suma) {
		double s1 = parseDouble(suma.getSumando1());
		double s2 = parseDouble(suma.getSumando2());
		suma.setResultado(s1+s2);
	}
}
