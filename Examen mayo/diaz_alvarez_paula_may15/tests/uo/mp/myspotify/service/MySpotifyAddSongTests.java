package uo.mp.myspotify.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.myspotify.model.playlist.Playlist;
import uo.mp.myspotify.model.song.Song;
import uo.mp.myspotify.model.user.User;
import uo.mp.myspotify.service.exception.AppException;
import uo.mp.myspotify.service.player.ByArtistNamePlayer;
import uo.mp.myspotify.service.player.SequentialPlayer;

class MySpotifyAddSongTests {

	private MySpotify service;
	private User other ;
	private Playlist playlistRelax, notExistentPlaylist;
	private Song song, songInRelax, songInDrive, notLicensedSong, repeatedSongInRelax;
	
	@BeforeEach
	void setup () {
		try {
			service = new MySpotify().configure();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		other = new User("other", new ByArtistNamePlayer());
		
		notExistentPlaylist = new Playlist("notExistentPlaylist", new LinkedList<>());
		playlistRelax = service.getPlayList("relax");
		
		song = new Song("Elvis Costello","She"); // a licensed Song
		songInDrive = new Song("The doors", "White child");  // a song in drive playlist
		notLicensedSong = new Song("Where the streets have no name", "U2");
		repeatedSongInRelax = new Song("Dido","White flag"); // a song in relax playlist
	}
	
	/**
	 * GIVEN: a valid username, playlist and song
	 * WHEN: addSong
	 * THEN: the song is added
	 */
	@Test
	void validCase() {
		try {
			assertEquals(true,service.addSong(other,playlistRelax,songInDrive));
		} catch (AppException e) {
			fail("no exception should be thrown");
		}
	}
	
	/**
	 * GIVEN: a valid username, playlist and a repeated song
	 * WHEN: addSong
	 * THEN: the song is not added
	 */
	@Test
	void repeatedSong() {
		try {
			assertEquals(false,service.addSong(other,playlistRelax,repeatedSongInRelax));
			
		} catch (AppException e) {
			fail("no exception should be thrown");
		}
	}
	
	/**
	 * GIVEN: a valid username, playlist and a non-licensed song
	 * WHEN: addSong
	 * THEN: an AppException is thrown
	 */
	@Test
	void nonLicensedSong() {
		try {
			service.addSong(other,playlistRelax,notLicensedSong);
			fail("an exception should be thrown");
		} catch (AppException e) {
			assertEquals(String.format("Not-licensed song %s, %s, %s",notLicensedSong.getArtist(), notLicensedSong.getTitle(), notLicensedSong.getDuration()),e.getMessage());
		}
	}
	
	/**
	 * GIVEN: a valid username, non existing playlist and a song
	 * WHEN: addSong
	 * THEN: an AppException is thrown
	 */
	@Test
	void nonExistingPlayList() {
		try {
			service.addSong(other,notExistentPlaylist,songInDrive);
			fail("an exception should be thrown");
		} catch (AppException e) {
			assertEquals(String.format("PlayList %s is not available",notExistentPlaylist.getId()),e.getMessage());
		}
	}
	
	/**
	 * GIVEN: a non logged username, playlist and a song
	 * WHEN: addSong
	 * THEN: an AppException is thrown
	 */
	@Test
	void nonLoggerUserPlayList() {
		User a=new User("aa", new ByArtistNamePlayer());
		try {
			service.addSong(a,playlistRelax,songInDrive);
			fail("an exception should be thrown");
		} catch (AppException e) {
			assertEquals(String.format("User %s is not logged",a.getId()),e.getMessage());
		}
	}
	
	/**
	 * GIVEN: a null username
	 * WHEN: addSong
	 * THEN: an AppException is thrown
	 */

}
