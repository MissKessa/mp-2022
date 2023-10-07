package uo.mp.myspotify.service.player.comparators;

import java.util.Comparator;

import uo.mp.myspotify.model.song.Song;

public class SongComparatorByArtist implements Comparator<Song>{

	@Override
	public int compare(Song s1, Song s2) {
		final int compareArtist = s1.getArtist().compareTo(s2.getArtist());
		return compareArtist;
	}

}
