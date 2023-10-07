package uo.mp.myspotify.xxx;

import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;

import uo.mp.myspotify.model.playlist.Playlist;
import uo.mp.myspotify.model.song.Song;
import uo.mp.myspotify.model.user.User;
import uo.mp.myspotify.service.MySpotify;
import uo.mp.myspotify.service.player.SequentialPlayer;

/*
 * Complete this class  for testing
 */
class renameTests {

	private MySpotify service;
	private User other ;
	private Playlist playlistRelax, notExistentPlaylist;
	private Song song, songInRelax, songInDrive, notLicensedSong, repeatedSongInRelax;
	
	@BeforeEach
	void setup () {
		service = new MySpotify().configure();
		
		other = new User("other", new SequentialPlayer());
		
		notExistentPlaylist = new Playlist("notExistentPlaylist", new LinkedList<>());
		playlistRelax = service.getPlayList("relax");
		
		song = new Song("Elvis Costello","She"); // a licensed Song
		songInDrive = new Song("The doors", "White child");  // a song in drive playlist
		notLicensedSong = new Song("Where the streets have no name", "U2");
		repeatedSongInRelax = new Song("Dido","White flag"); // a song in relax playlist
	}
	
	
}
