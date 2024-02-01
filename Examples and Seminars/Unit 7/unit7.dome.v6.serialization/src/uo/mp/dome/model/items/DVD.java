package uo.mp.dome.model.items;

import java.io.PrintStream;

@SuppressWarnings("serial")
public class DVD extends Item  {
	private String director;
	private int playingTime;
	
	public DVD(String title, String director, int playingTime) {
		super(title);
		this.playingTime = playingTime;
		this.director = director;
	}

	@Override
	public void print(PrintStream out) {
		out.print("DVD: ");
		super.print( out );
		out.println( "playing time " + playingTime + " mins) " + director);
	}

	public String getDirector() {
		return this.director;
	}
	
	public int getPlayingTime() {
		return this.playingTime;
	}

	@Override
	public boolean equals(Object item) {
		if ( ! (item instanceof DVD) ) return false;
		
		DVD dvd = (DVD) item;
		return dvd.getTitle().equals( getTitle() )
				&& dvd.getDirector().equals( getDirector() );
	}
	
	

}
