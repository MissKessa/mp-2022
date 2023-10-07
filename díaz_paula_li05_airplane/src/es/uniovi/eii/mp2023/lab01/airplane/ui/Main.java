package es.uniovi.eii.mp2023.lab01.airplane.ui;

import es.uniovi.eii.mp2023.lab01.airplane.model.Person;
import es.uniovi.eii.mp2023.lab01.airplane.model.Plane;

public class Main {

  public static void main(String[] args) {
    // TODO: Create an Person and a a Plane with the person as pilot, 'D' as identifier and 10000
    // as fuel. Finally print the plane String representation.
	  Person willy = new Person("willy",27);
	  Plane plane = new Plane (willy,'D',1000);
	  
	  System.out.println(plane);
	  // Don't do this: System.out.println(plane.toString());
  }

}
