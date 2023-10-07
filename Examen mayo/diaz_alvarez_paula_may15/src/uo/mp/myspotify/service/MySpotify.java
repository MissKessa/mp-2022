package uo.mp.myspotify.service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import uo.mp.myspotify.model.playlist.Playlist;
import uo.mp.myspotify.model.user.User;
import uo.mp.myspotify.service.exception.AppException;
import uo.mp.myspotify.service.parser.SongsParser;
import uo.mp.myspotify.service.player.ByArtistNamePlayer;
import uo.mp.myspotify.service.player.SequentialPlayer;
import uo.mp.myspotify.service.serializer.MySpotifySerializer;
import uo.mp.util.check.ArgumentChecks;
import uo.mp.util.file.FileUtil;
import uo.mp.myspotify.model.song.Song;



public class MySpotify {

	
	private static final String LICENSED_SONGS_FILENAME = "licensedSongs.txt";
	private static final String PLAYLISTS_FILENAME = "playlists.txt";
	
	private List<User> registeredUsers=new ArrayList<>();
	private List<User> loggedUsers=new ArrayList<>();
	private List<Song> licensedSongs=new ArrayList<>();
	private List<Playlist> playlists=new ArrayList<>();


	/**
	 * Default configuration
	 * @return this same instance
	 * @throws FileNotFoundException 
	 * @throws DO NOT
	 */
	public MySpotify configure() throws FileNotFoundException {
		// 1. add users
		addUsers(); // already implemented

		// 2. load existing songs from file
		loadRegisteredSongs(LICENSED_SONGS_FILENAME);   // TO DO

		// 3.- configure playlists
		addPlaylists();    // already implemented

		return this;
	}

	/**
	 * This method is fully implemented. It registers two user in the app so they
	 * can be used in the the simulation
	 */
	private void addUsers() {
		registeredUsers.add(new User("user", new SequentialPlayer()));
		registeredUsers.add(new User("other", new ByArtistNamePlayer()));
	}

	/*
	 * It fills the list of registered songs with correct songs in the file
	 * 
	 * songsFileName the name of the file containing licensed songs,
	 *  one per line
	 */
	private void loadRegisteredSongs(String songsFileName) throws FileNotFoundException  {
		ArgumentChecks.notNull(songsFileName, "The songs file name cannot be null");
		ArgumentChecks.notBlank(songsFileName, "The songs file name cannot be blank");
		ArgumentChecks.notEmpty(songsFileName, "The songs file name cannot be empty");
		FileUtil file=new FileUtil();
		List<String> songsString = file.readLines(songsFileName);
		List<Song> songs = new SongsParser().parse(songsString);
		this.licensedSongs.addAll(songs);
		
		
	}

	/**
	 * this method is fully implemented It creates three default playlists in
	 * mySpotify as described in the document, so they can be used in the simulation
	 */
	private void addPlaylists() {
		List<Song> pairSongs = new LinkedList<>();
		for (int i = 0; i < this.licensedSongs.size(); i += 2) {
			pairSongs.add(licensedSongs.get(i));
		}

		List<Song> oddSongs = new LinkedList<>();
		for (int i = 1; i < this.licensedSongs.size(); i += 2) {
			oddSongs.add(licensedSongs.get(i));
		}

		Playlist drivePL = new Playlist("drive", pairSongs);
		Playlist relaxPL = new Playlist("relax", oddSongs);
		Playlist partyPL = new Playlist("party", this.licensedSongs);
		playlists.add(drivePL);
		playlists.add(relaxPL);
		playlists.add(partyPL);

	}

	public User login(String id) throws AppException {
		ArgumentChecks.notNull(id, "The id cannot be null");
		ArgumentChecks.notBlank(id, "The id cannot be blank");
		ArgumentChecks.notEmpty(id, "The id cannot be empty");

		
		for (User registered:this.registeredUsers ) {
			if (registered.getId().equals(id))
				this.loggedUsers.add(registered);
				return registered;
		}
			
		throw new AppException(String.format("User %s is not registered",id));
	}


	public boolean addSong(User user, Playlist playlist, Song song) throws AppException {
		ArgumentChecks.notNull(user, "The user cannot be null");
		ArgumentChecks.notNull(playlist, "The playlist cannot be null");
		ArgumentChecks.notNull(song, "The song cannot be null");
		
		if (!isUserLogged(user))
			throw new AppException(String.format("User %s is not logged",user.getId()));
		if (!isPlayListAvailable(playlist))
			throw new AppException(String.format("PlayList %s is not available",playlist.getId()));
		if (!isALicensedSong(song))
			throw new AppException(String.format("Not-licensed song %s, %s, %s",song.getArtist(), song.getTitle(), song.getDuration()));
		
		return playlist.addSong(song);
	}
	
	private boolean isALicensedSong(Song song) {
		ArgumentChecks.notNull(song, "The song cannot be null");
		for (Song licensedSong:this.licensedSongs ) {
			if (licensedSong.equals(song))
				return true;
		}
		return false;
	}

//	private boolean isUserRegistered(User user) {
//		ArgumentChecks.notNull(user, "The user cannot be null");
//		for (User registered:this.registeredUsers ) {
//			if (registered.equals(user))
//				return true;
//		}
//		return false;
//	}
	
	private boolean isPlayListAvailable(Playlist playlist) {
		ArgumentChecks.notNull(playlist, "The playlist cannot be null");
		for (Playlist existingPlayList:this.playlists ) {
			if (existingPlayList.equals(playlist))
				return true;
		}
		return false;
	}
	
//	private boolean isPlayListAvailable(String id) {
//		ArgumentChecks.notNull(id, "The id cannot be null");
//		ArgumentChecks.notBlank(id, "The id cannot be blank");
//		ArgumentChecks.notEmpty(id, "The id cannot be empty");
//		for (Playlist existingPlayList:this.playlists ) {
//			if (existingPlayList.getId().equals(id))
//				return true;
//		}
//		return false;
//	}
	
	private boolean isUserLogged(User user) {
		ArgumentChecks.notNull(user, "The user cannot be null");
		for (User logged:this.loggedUsers) {
			if (logged.equals(user))
				return true;
		}
		return false;
	}

	public void close() {
		MySpotifySerializer serializer=new MySpotifySerializer();
		List<String> lines = serializer.serialize(getPlayLists());
		FileUtil fileutil =new FileUtil();
		fileutil.writeLines(PLAYLISTS_FILENAME, lines);
	}

	public String play(User user, Playlist playlist) throws AppException {
		ArgumentChecks.notNull(user, "The user cannot be null");
		ArgumentChecks.notNull(playlist, "The playlist cannot be null");
		
		if (!isUserLogged(user))
			throw new AppException(String.format("User %s is not logged",user.getId()));
		if (!isPlayListAvailable(playlist))
			throw new AppException(String.format("PlayList %s is not available",playlist.getId()));
		return user.getPlayer().play(playlist);

	}

	public Playlist getPlayList(String id) {
		ArgumentChecks.notNull(id, "The id cannot be null");
		ArgumentChecks.notBlank(id, "The id cannot be blank");
		ArgumentChecks.notEmpty(id, "The id cannot be empty");
		
			for (Playlist existingPlayList:this.playlists ) {
				if (existingPlayList.getId().equals(id))
					return existingPlayList;
			}
		return null;
	}
	
	private List<Playlist> getPlayLists(){
		List<Playlist> copy=new ArrayList<>();
		for (Playlist playlist:this.playlists)
			copy.add(playlist);
		return copy;
	}

}
