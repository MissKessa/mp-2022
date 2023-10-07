package uo.mp.myspotify.model.playlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import uo.mp.myspotify.model.song.Song;
import uo.mp.util.check.ArgumentChecks;

public class Playlist {
	private String id;
	private List<Song> songs=new ArrayList<>();
	
	public Playlist(String id, List<Song> pairSongs) {
		ArgumentChecks.notNull(id, "The id cannot be null");
		ArgumentChecks.notBlank(id, "The id cannot be blank");
		ArgumentChecks.notEmpty(id, "The id cannot be empty");
		ArgumentChecks.notNull(pairSongs, "The pairSongs cannot be null");
		List<Song> songs=new ArrayList<>();
		for (Song song:pairSongs) {
			ArgumentChecks.notNull(song, "The pairSongs cannot be have null songs");
			songs.add(song);
		}
		this.id=id;
		this.songs=songs;
	}

	public List<Song> getSongs() {
		List<Song> copy=new ArrayList<>();
		for (Song song:this.songs) {
			copy.add(song);
		}
		return copy;
	}
	
	private boolean isSongInThePlayList(Song song) {
		ArgumentChecks.notNull(song, "The song cannot be null");
		for (Song songIn : this.songs) {
			if (songIn.equals(song))
				return false;
		}
		return true;
	}
	
	public boolean addSong(Song song) {
		ArgumentChecks.notNull(song, "The song cannot be null");
		if (!isSongInThePlayList(song)) {
			this.songs.add(song);
			return true;
		}
		return false;
	}

	public List<String> serialize() {
		List<String> lines = new ArrayList<String>();
		lines.add(String.format("Playlist %s", id));
		for (Song song:this.songs) {
			lines.add(song.serialize());
		}
		return lines;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Playlist other = (Playlist) obj;
		return Objects.equals(id, other.id);
	}

	public String getId() {
		return id;
	}
	
	
	

}
