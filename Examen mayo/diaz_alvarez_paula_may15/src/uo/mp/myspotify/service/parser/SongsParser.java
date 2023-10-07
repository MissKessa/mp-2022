package uo.mp.myspotify.service.parser;

import java.util.ArrayList;
import java.util.List;

import uo.mp.myspotify.model.song.Song;
import uo.mp.myspotify.service.exception.InvalidLineFormatException;
import uo.mp.util.check.ArgumentChecks;
import uo.mp.util.log.Logger;

public class SongsParser {
	private int lineNumber;
	
	public List<Song> parse(List<String> lines) {
		ArgumentChecks.notNull(lines, "The list of lines cannot be null");
		List<Song> songs = new ArrayList<>();
		for (String line:lines) {
			ArgumentChecks.notNull(line, "The list of lines cannot be null");
			try {
			Song song =parseLine(line);
			songs.add(song);
			} catch(InvalidLineFormatException e) {
				Logger.log(String.format("PARSING ERROR at line %s: %s", lineNumber, e.getMessage()));
			}
			
			this.lineNumber++;
		}
		return songs;
	}

	private Song parseLine(String line) throws InvalidLineFormatException {
		ArgumentChecks.notNull(line, "The list of lines cannot be null");
		if (line.isBlank() || line.isEmpty())
			throw new InvalidLineFormatException("Incorrect empty line");
		
		String[] parts=line.split(";");
		if (!(parts.length==2 ||parts.length==3 ))
			throw new InvalidLineFormatException("Incorrect number of fields");
		
		String artist=parts[0];
		String title=parts[1];
		double duration;
		if (parts.length==3)
			duration=parseDouble(parts[2]);
		else
			duration=Song.DEFAULT_DURATION;
		if (duration<0)
			throw new InvalidLineFormatException("Incorrect number format");
		return new Song(artist,title,duration);
	}

	private double parseDouble(String string) throws InvalidLineFormatException {
		try {
			return Double.parseDouble(string);
		}catch(NumberFormatException e) {
			throw new InvalidLineFormatException("Incorrect number format");
		}
	}
	
}
