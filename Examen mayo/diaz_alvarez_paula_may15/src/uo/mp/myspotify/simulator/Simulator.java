package uo.mp.myspotify.simulator;


import java.io.FileNotFoundException;

import uo.mp.myspotify.model.playlist.Playlist;
import uo.mp.myspotify.model.user.User;
import uo.mp.myspotify.service.MySpotify;
import uo.mp.myspotify.service.exception.AppException;
import uo.mp.util.console.Console;
import uo.mp.myspotify.model.song.Song;

/**
 * This class simulates a scenario of interaction between a registered user and the
 * application
 * You may change it at your wish to test different scenarios. 
 */
public class Simulator {

	private MySpotify mySpotify;
	
	private Playlist drivePL, relaxPL;

	/* Some songs */
	private Song flowersMileyCyrus = new Song("Miley Cyrus", "Flowers");

	/**
	 * There is no user interface for this small program. The following methods
	 * simulate an example scenario of use.
	 * @throws AppException 
	 * @throws FileNotFoundException 
	 * 
	 */
	public void start() throws AppException, FileNotFoundException {
		simulate();
	}
	
	private void simulate() throws AppException, FileNotFoundException {
		/*
		 * 1.- creates an application configured with predefined values:
		 * - two default users, one with a sequential player other with a player by artist name 
		 * - songs loaded from licensedSongs.txt file 
		 * - three default playlists
		 */
		mySpotify = new MySpotify().configure();

		/* 2.- get playlists by id */
		drivePL = mySpotify.getPlayList("drive");
		relaxPL = mySpotify.getPlayList("relax");

		/* 3.- Do some operations with one user */
		doSomething();
		
		/* 4.- user closes the app */
		mySpotify.close();
	}

	private void doSomething() throws AppException  {
		User user, other; 
		
		/* 1.- registered user login mySpotify */
		user = login("user");

		/* 2.- user plays the playlist in the app */
		play(user);
		
		/* 3.- user adds a licensed song to the playlist */
		addSong(user);
		
		/* 4.- And again, same user, song, playlist */
		addSong(user);
		
		/* 6.- user plays the playlist in the app */
		play(user);	
		
        /* 7.- login other (for playing ordered playlist */
		
		other = login("other");
		play(other);
	}

	private User login(String userId) throws AppException {
		return  mySpotify.login(userId);
	}

	private void play(User user) throws AppException {
		Console.print("\nPlaying ... \n");    // change to public en Console if it is private
		String result = mySpotify.play(user, relaxPL);

		Console.print(result);
		Console.print("------------------\n");
	}

	/*
	 * users add songs 
	 */
	private void addSong(User user) throws AppException   {

		/* Logged users can add new songs to the playlist */
		
		mySpotify.addSong(user, relaxPL, flowersMileyCyrus);

		/*
		 * Other situations you may want to execute
		 */
//		/* Users cannot add not registered songs */
//		mySpotify.addSong(user, drivePL, blowinInTheWindBobDylan);
//		/* Users cannot add songs already in the list */
//		mySpotify.addSong(user, drivePL, flowersMileyCirus);

	}


}
