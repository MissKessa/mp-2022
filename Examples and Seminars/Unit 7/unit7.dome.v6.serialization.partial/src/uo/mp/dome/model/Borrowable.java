package uo.mp.dome.model;

public interface Borrowable {
	boolean isAvailable();
	void borrow();
	void giveBack();
}
