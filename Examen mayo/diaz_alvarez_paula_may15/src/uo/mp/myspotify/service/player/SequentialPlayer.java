package uo.mp.myspotify.service.player;

import java.util.List;

import uo.mp.myspotify.model.playlist.Playlist;
import uo.mp.myspotify.model.song.Song;
import uo.mp.util.check.ArgumentChecks;

public class SequentialPlayer extends Player{

	@Override
	public String play(Playlist pl) {
		ArgumentChecks.notNull(pl, "The playlist cannot be null");
		StringBuilder sb = new StringBuilder();
		List<Song> songs = pl.getSongs();
		for (Song s : songs)
			sb.append(s.play());
		String result = sb.toString();
		return result;
	}

}
