package uo.mp.myspotify.service.serializer;

import java.util.ArrayList;
import java.util.List;

import uo.mp.myspotify.model.playlist.Playlist;
import uo.mp.util.check.ArgumentChecks;

public class MySpotifySerializer {

	public List<String> serialize(List<Playlist> playLists) {
		ArgumentChecks.notNull(playLists, "The playlist cannot be null");
		List<String> lines = new ArrayList<String>();
		for (Playlist playlist:playLists) {
			ArgumentChecks.notNull(playlist, "The playlist cannot have null elements");
			lines.addAll(playlist.serialize());
		}
		return lines;
	}

}
