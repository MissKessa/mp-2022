package uo.mp.dome.model.items;

import java.io.PrintStream;

import uo.mp.dome.model.Borrowable;

public class CD extends Item implements Borrowable {

	private String artist;
	private int playingTime;
	private int numberOfTracks;
	private boolean available;
	
	public CD(String title, String artist, int playingTime, int numberOfTracks) {
		super(title);
		this.playingTime = playingTime;
		this.artist= artist;
		this.numberOfTracks = numberOfTracks;
		this.available = false;
	}

	@Override
	public void print(PrintStream out) {
		out.print("CD: ");
		super.print(out);
		out.print("playing time " + playingTime + " mins) " + getArtist() );
		out.println(" tracks: " + getNumberOfTracks() );
	}
	
	public String getArtist() {
		return this.artist;
	}

	public int getNumberOfTracks() {
		return this.numberOfTracks;
	}
	
	public int getPlayingTime() {
		return this.playingTime;
	}

	@Override
	public boolean equals(Object item) {
		if ( ! (item instanceof CD) ) return false;
		
		CD cd = (CD) item;
		return cd.getTitle().equals( getTitle() )
				&& cd.getArtist().equals( getArtist() );
	}

	@Override
	public boolean isAvailable() {
		return this.getOwn() && available;
	}

	@Override
	public void borrow() {
		this.available = false;
	}

	@Override
	public void giveBack() {
		this.available = true;
	}
	
}