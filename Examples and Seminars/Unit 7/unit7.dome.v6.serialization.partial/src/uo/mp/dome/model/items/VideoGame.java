package uo.mp.dome.model.items;

import java.io.PrintStream;


public class VideoGame extends Item {

	private Platform platform;
	private int numberOfPlayers;
	
	public VideoGame(String theTitle, int numberOfPlayers, Platform platform) {
		super(theTitle);
		this.numberOfPlayers =  numberOfPlayers;
		this.platform = platform;
	}
	
	public int getNumberOfPlayers() {
		return this.numberOfPlayers;
	}
	
	public Platform getPlatform() {
		return this.platform;
	}

	@Override
	public void print(PrintStream out) {
		out.print("VideoGame: ");
		super.print(out);
		out.print("platform: " + platform);
		out.println("Number of players: " + numberOfPlayers );
	}

	@Override
	public boolean equals(Object item) {
		if ( ! (item instanceof VideoGame) ) return false;
		
		VideoGame vg = (VideoGame) item;
		return vg.getTitle().equals( getTitle() )
				&& vg.getPlatform() == platform;
	}
	

}