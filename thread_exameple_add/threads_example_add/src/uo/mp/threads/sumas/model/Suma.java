package uo.mp.threads.sumas.model;

/**
 * La clase SUMA contiene sumandos representados como un String, 
 * pero no sabe calcular el resultado.
 * 
 * El resultado lo recibe a través de un setter
 * 
 * @author Dani
 *
 */
public class Suma {
	
	private String sumando1;
	private String sumando2;
	
	private double resultado;
	

	public Suma(String sumando1, String sumando2) {
		this.sumando1 = sumando1;
		this.sumando2 = sumando2;
		
		//NO SÉ CALCULAR EL RESULTADO
	}
	
	public double getResultado() {
		return resultado;
	}


	public void setResultado(double resultado) {
		this.resultado = resultado;
	}


	public String getSumando1() {
		return sumando1;
	}


	public String getSumando2() {
		return sumando2;
	}


}
