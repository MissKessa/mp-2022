package uo.mp.lab03.dome.model;

public interface Borrowable {
	public boolean isLoaned();

	public void giveBack();

	public void borrow();

	// public boolean isAvailable();
}
