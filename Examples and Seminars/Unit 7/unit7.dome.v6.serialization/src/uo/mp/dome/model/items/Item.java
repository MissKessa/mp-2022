package uo.mp.dome.model.items;

import java.io.PrintStream;
import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class Item implements Serializable {

	private String title;
	
	private boolean gotIt;
	private String comment;

	public Item(String title) {
		this.title = title;
		gotIt = false;
		comment = "";
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getComment() {
		return comment;
	}

	public void setOwn(boolean ownIt) {
		gotIt = ownIt;
	}

	public boolean getOwn() {
		return gotIt;
	}

	public String getTitle() {
		return this.title;
	}

	public void print(PrintStream out) {
		out.print( getTitle() );
		if ( getOwn() ) {
			out.print("*");
		}
		out.print(" " + getComment() );
	}

}