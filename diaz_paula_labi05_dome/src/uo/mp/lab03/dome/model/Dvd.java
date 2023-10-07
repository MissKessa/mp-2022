package uo.mp.lab03.dome.model;

import java.util.Objects;

import uo.mp.util.check.ArgumentChecks;

/**
 * In the context of the multimedia library, a DVD corresponds to an item that
 * also has a director. For the rest of the fields inherited from Item it takes
 * the default values specified in the Item class. In the same way DVD is an
 * almost immutable object, only its comment can be modified once it has been
 * created.
 *
 * @author Programming Methodology 2023 Teaching Staff
 * @version 2023
 */
public class Dvd extends Item {
	/**
	 * It's the value of the taxes included in the total price of the dvd
	 */
	public final static double TAXES = 0;

	private final String director;
	private final int playingTime;

	/**
	 * Main builder with parameters to create a DVD. This constructor takes the
	 * title, the director and the playingTime and creates a DVD instance. To do so,
	 * it uses the default values of those fields inherited from Item that have not
	 * been used and, after validating the rest of the values given in the
	 * constructor, it assigns the rest to the instance.
	 * 
	 * @param title       is the value to be given to the dvd title. It cannot be
	 *                    null, empty or blank.
	 * @param basePrice   is the base price that will be assigned to the created
	 *                    item. It cannot be negative or greater than
	 *                    {@value #MAXIMUM_BASE_PRICE}
	 * @param director    is the name of the director of the DVD. It cannot be null,
	 *                    empty or blank.
	 * @param playingTime indicates the length of the DVD in minutes. If must be a
	 *                    positive integer.
	 * @throws IllegalArgumentException if any of the previous parameters value is
	 *                                  not valid.
	 */
	public Dvd(String title, double basePrice, String director, int playingTime) {
		super(title, basePrice);
		ArgumentChecks.isTrue(director != null, "The director cannot be null.");
		ArgumentChecks.isTrue(!director.trim().isEmpty(), "The director cannot be empty nor blank.");
		this.director = director;

		ArgumentChecks.isTrue(playingTime > 0, "The playing time must be greater then 0.");
		this.playingTime = playingTime;
	}

	/**
	 * @return the value of the artist as a non-zero, non-empty and non-blank
	 *         string.
	 */
	public String getDirector() {
		return this.director;
	}

	/**
	 * @return the total playing time time of the CD in minutes, expressed as a
	 *         positive integer.
	 */
	public int getPlayingTime() {
		return this.playingTime;
	}

	/**
	 * Returns the String representation of a Dvd. The format is as follow "Dvd
	 * [title=%s, inStock=%s, comment=%s, director=%s, playingTime=%s]".
	 */
	@Override
	public String toString() {
		return String.format("Dvd [code=%s, inStock=%s, comment=%s, playingTime=%s]", this.getCode(), this.inStock(),
				this.getComment(), this.playingTime);
	}

	/**
	 * Compares if an item and a dvd are equal or not in contents
	 * 
	 * @param item is the item we want to compare with this dvd
	 * @return True if the item is equal (has the same title and director) to this
	 *         dvd
	 */
	@Override
	public boolean equals(Item item) {
		if (!(item instanceof Dvd)) {
			return false;
		}
		if (!super.equals(item)) {
			return false;
		}
		Dvd dvd = (Dvd) item;
		return Objects.equals(director, dvd.director);
	}

	/**
	 * @return the value of the responsible person of the dvd (director) as a
	 *         non-zero, non-empty and non-blank string.
	 */
	@Override
	public String getResponsiblePerson() {
		return director;
	}

	/**
	 * @return the total price of the dvd (base price+ taxes)
	 */
	@Override
	public double getTotalPrice() {
		return this.getBasePrice() + TAXES;
	}

}
