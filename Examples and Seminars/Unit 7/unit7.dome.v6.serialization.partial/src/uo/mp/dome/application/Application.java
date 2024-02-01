package uo.mp.dome.application;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import uo.mp.dome.model.MediaLibrary;

public class Application {
	
	private static final String FILE_NAME = "dome.database.dat";
	
	private MediaLibrary mediaLibrary = new MediaLibrary();

	public void retrieveDatabase() {
		try {
			ObjectInputStream file = new ObjectInputStream(
				new BufferedInputStream(
					new FileInputStream( FILE_NAME )
				)
			);

			try {
				this.mediaLibrary = (MediaLibrary) file.readObject();
			} finally {
				file.close();
			}

		
		} catch (FileNotFoundException e) {
			// That might be the first execution
		} catch (ClassNotFoundException | IOException e) {
			throw new RuntimeException( e );
		}
	}

	public void updateDatabase() {
	
		try {
		    ObjectOutputStream file = 
			    new ObjectOutputStream(
					new BufferedOutputStream(
						new FileOutputStream( FILE_NAME )
					)
				);

			try {
				file.writeObject( this.mediaLibrary );
			} finally {
				file.close();
			}
		
		} catch (IOException e) {
			throw new RuntimeException( e );
		}
	}

}
