package uo.mp.lab02.example01;

public class Person {

	private String name;
	private DNI dni;

  	public Person(String name) {
  		setName(name);
  	}

  	public void setName(String name) {
  		this.name = name;
  	}

	public void setDNI(DNI dni) {
		if (this.dni !=null) {
			throw new IllegalStateException("sadas");
		}
		this.dni = dni;
	}

}
