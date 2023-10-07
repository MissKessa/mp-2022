package uo.mp.myspotify.model.song;

import java.util.Objects;

public class Song {

	public static final double DEFAULT_DURATION=3.0;
	private String artist;
	private String title;
	private double duration;
	
	public Song(String artist, String title) {
		this(artist,title,DEFAULT_DURATION);
	}
	
	

	public Song(String artist, String title, double duration) {
		super();
		this.artist = artist;
		this.title = title;
		this.duration = duration;
	}



	public String play() {
		return String.format("%s,%s,%s\n", artist, title,duration);
	}



	@Override
	public int hashCode() {
		return Objects.hash(artist, title);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Song other = (Song) obj;
		return Objects.equals(artist, other.artist) && Objects.equals(title, other.title);
	}



	public String getArtist() {
		return artist;
	}



	public String getTitle() {
		return title;
	}



	public double getDuration() {
		return duration;
	}



	public String serialize() {
		return String.format("%s,%s", title,artist);
	}
	
	

}
