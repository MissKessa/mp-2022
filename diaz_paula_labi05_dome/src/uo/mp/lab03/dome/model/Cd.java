package uo.mp.lab03.dome.model;

import java.util.Objects;

import uo.mp.util.check.ArgumentChecks;

/**
 * In the context of the multimedia library, a CD is an item that also has an
 * artist and a certain number of tracks. The CD has no default value except for
 * the comment which inherits the default value from Item.COMMENT_DEFAULT_VALUE.
 * In addition a CD is a mostly immutable object. Once created it is not allowed
 * to change any of its values except for the comment.
 *
 * @author Programming Methodology 2023 Teaching Staff
 * @version 2023
 */
public class Cd extends Item implements Borrowable {
	/**
	 * It's the value of the taxes included in the total price of the cd
	 */
	public final static double TAXES = 2;

	private final String artist;
	private final int numberOfTracks;
	private final int playingTime;
	private boolean loaned;

	/**
	 * Main constructor. This builder takes all the necessary data such as title,
	 * basePrice artist, number of tracks and playing time to create a CD. The item
	 * comment is initialized with the default value of Item.COMMENT_DEFAULT_VALUE.
	 * 
	 * @param title          is the string value for the title of the CD. It must be
	 *                       not-null, non-empty and non-blank. Otherwise an
	 *                       IllegalArgumentException is thrown.
	 * 
	 * @param basePrice      is the base price that will be assigned to the created
	 *                       item. It cannot be negative or greater than
	 *                       {@value #MAXIMUM_BASE_PRICE}
	 * @param artist         is the string representation of the artist of the CD.
	 *                       It must be not-null, non-empty and non-blank. Otherwise
	 *                       an IllegalArgumentException is thrown.
	 * @param numberOfTracks is a positive integer that indicates how many tracks
	 *                       are included in the CD. If the value is not positive
	 *                       then an IlleganAlgurmentException is thrown.
	 * @param playingTime    indicates the length of the CD in minutes. If must be a
	 *                       positive integer or else an IllegalArgumentException is
	 *                       thrown.
	 * @throws IllegalArgumentException if any of the previous parameters value is
	 *                                  not valid.
	 */
	public Cd(String title, double basePrice, String artist, int numberOfTracks, int playingTime) {
		super(title, basePrice);

		ArgumentChecks.isTrue(artist != null, "The artist cannot be null.");
		ArgumentChecks.isTrue(!artist.trim().isEmpty(), "The artist cannot be empty nor blank.");
		this.artist = artist;

		ArgumentChecks.isTrue(numberOfTracks > 0, "The number of tracks must be greater then 0.");
		this.numberOfTracks = numberOfTracks;

		ArgumentChecks.isTrue(playingTime > 0, "The playing time must be greater then 0.");
		this.playingTime = playingTime;

		this.loaned = false;
	}

	/**
	 * @return if the book is loaned of not
	 */
	@Override
	public boolean isLoaned() {
		return loaned;
	}

	/**
	 * Sets the cd to not loaned
	 */
	@Override
	public void giveBack() {
		this.loaned = false;
	}

	/**
	 * Sets the cd to loaned
	 */
	@Override
	public void borrow() {
		this.loaned = true;
	}

	/**
	 * @return if a cd is available or not (inStock and not loaned)
	 */
	@Override
	public boolean isAvailable() {
		return super.isAvailable() && !this.loaned;
	}

	/**
	 * @return the value of the artist as a non-zero, non-empty and non-blank
	 *         string.
	 */
	public String getArtist() {
		return this.artist;
	}

	/**
	 * @return the number of tracks contained on the CD as a positive integer.
	 */
	public int getNumberOfTracks() {
		return this.numberOfTracks;
	}

	/**
	 * @return the total playing time time of the CD in minutes, expressed as a
	 *         positive integer.
	 */
	public int getPlayingTime() {
		return this.playingTime;
	}

	/**
	 * Returns the String representation of a Cd. The format is as follow "Cd
	 * [title=%s, inStock=%s, comment=%s, artist=%s, numberOfTracks=%s,
	 * playingTime=%s]".
	 */
	@Override
	public String toString() {
		return String.format("Cd [code=%s, inStock=%s, comment=%s, numberOfTracks=%s, playingTime=%s]", this.getCode(),
				this.inStock(), this.getComment(), this.numberOfTracks, this.playingTime);
	}

	/**
	 * Compares if an item and a cd are equal or not in contents
	 * 
	 * @param item is the item we want to compare with this cd
	 * @return True if the item is equal (has the same title and artist)
	 */
	@Override
	public boolean equals(Item item) {
		if (!(item instanceof Cd)) {
			return false;
		}
		if (!super.equals(item)) {
			return false;
		}
		Cd cd = (Cd) item;
		return Objects.equals(artist, cd.artist);
	}

	/**
	 * @return the value of the responsible person of the CD (artist) as a non-zero,
	 *         non-empty and non-blank string.
	 */
	@Override
	public String getResponsiblePerson() {
		return artist;
	}

	/**
	 * @return the total price of the cd (base price+ taxes)
	 */
	@Override
	public double getTotalPrice() {
		return this.getBasePrice() + TAXES;
	}

}
