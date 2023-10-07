package uo.mp.lab03.dome.model;

import java.util.Objects;

import uo.mp.util.check.ArgumentChecks;

/**
 * In the context of the multimedia library, a Videogame is an item that also
 * has an author, a owner, a number of players and a platform. The Videogame has
 * no default value except for the comment which inherits the default value from
 * Item.COMMENT_DEFAULT_VALUE. In addition a Videogame is a mostly immutable
 * object. Once created it is not allowed to change any of its values except for
 * the owner and the comment.
 *
 * @author Paula Díaz Álvarez
 * @version 2023
 */
public class Videogame extends Item {

	private final String author;
	private String owner;
	private final int numberOfPlayers;
	private final VideogamePlatform platform;

	/**
	 * Main builder with parameters to create a Videogame. This constructor takes
	 * the title, the author, the numberOfPlayers and platform and creates a
	 * Videogame instance. To do so, it uses the default values of those fields
	 * inherited from Item that have not been used and, after validating the rest of
	 * the values given in the constructor, it assigns the rest to the instance.
	 * 
	 * @param title           is the value to be given to the videogame title. It
	 *                        cannot be null, empty or blank.
	 * @param basePrice       is the base price that will be assigned to the created
	 *                        item. It cannot be negative or greater than
	 *                        {@value #MAXIMUM_BASE_PRICE}
	 * @param author          is the name of the author of the videogame. It cannot
	 *                        be null, empty or blank.
	 * @param numberOfPlayers indicates the number of players to play the videogame.
	 *                        If must be a positive integer.
	 * @param platform        is the platform of the videogame. It can only have one
	 *                        of the values of {@value #PLATFORMS}
	 * @throws IllegalArgumentException if any of the previous parameters value is
	 *                                  not valid.
	 */
	public Videogame(String title, double basePrice, String author, int numberOfPlayers, VideogamePlatform platform) {
		super(title, basePrice);
		ArgumentChecks.isTrue(author != null, "The author cannot be null.");
		ArgumentChecks.isTrue(!author.trim().isEmpty(), "The author cannot be empty nor blank.");
		this.author = author;

		ArgumentChecks.isTrue(numberOfPlayers > 0, "The number of players must be greater than 0.");
		this.numberOfPlayers = numberOfPlayers;
		/*
		 * boolean correctPlatform = false; for (String possiblePlatform : PLATFORMS) {
		 * if (platform.toUpperCase() == possiblePlatform) { correctPlatform = true; } }
		 * if (!correctPlatform) { throw new
		 * IllegalArgumentException("The platform is incorrect"); } this.platform =
		 * platform.toUpperCase();
		 */
		ArgumentChecks.isTrue(platform != null, "The platform cannot be null.");
		this.platform = platform;

	}

	/**
	 * @return the value of the owner of the videogame
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * Modifies the value of the owner of the current instance. To do so, it
	 * receives the new value of the owner as a String that must not be null, empty
	 * or blank.
	 * 
	 * @param owner is the new string value to set to the owner field of the
	 *              instance. Cannot be null, blank nor empty.
	 * @throws IllegalArgumentException if the owner is either null, empty or blank.
	 */
	public void setOwner(String owner) {
		ArgumentChecks.isTrue(owner != null, "The owner cannot be null.");
		ArgumentChecks.isTrue(!owner.isBlank(), "The owner cannot be empty nor blank.");
		this.owner = owner;
	}

	/**
	 * @return the value of the author as a non-zero, non-empty and non-blank
	 *         string.
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @return the number of players of the videogame, expressed as a positive
	 *         integer.
	 */
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	/**
	 * @return the value of the platform as a non-zero, non-empty and non-blank
	 *         string.
	 */
	public VideogamePlatform getPlatform() {
		return platform;
	}

	/**
	 * Returns the String representation of a Videogame. The format is as follow
	 * "Videogame [title=%s, inStock=%s, comment=%s, author=%s, owner=%s,
	 * numberOfPlayers=%s, platform=%s]".
	 */
	@Override
	public String toString() {
		return String.format("Videogame [code=%s, inStock=%s, comment=%s, owner=%s, numberOfPlayers=%s]",
				this.getCode(), this.inStock(), this.getComment(), this.owner, this.numberOfPlayers);
	}

	/**
	 * Compares if an item and a Videogame are equal or not in contents
	 * 
	 * @param item is the item we want to compare with this Videogame
	 * @return True if the item is equal (has the title and platform) to this
	 *         Videogame
	 */
	@Override
	public boolean equals(Item item) {
		if (!(item instanceof Videogame)) {
			return false;
		}
		if (!super.equals(item)) {
			return false;
		}
		Videogame game = (Videogame) item;
		return Objects.equals(platform, game.platform);
	}

	/**
	 * @return the value of the responsible person of the videogame (author) as a
	 *         non-zero, non-empty and non-blank string.
	 */
	@Override
	public String getResponsiblePerson() {
		return author;
	}

	/**
	 * @return the total price of the videogame (base price+ taxes)
	 */
	@Override
	public double getTotalPrice() {
		final double TAXES = this.getBasePrice() * 0.1;
		return this.getBasePrice() + TAXES;
	}

	/**
	 * Returns a string made up of the first 2 letters of the title, a hyphen, the
	 * first 2 letters of the person in charge and the platform. Everything must be
	 * in lower case.
	 */
	@Override
	public String getCode() {
		return String.format("%s-%s-%s", this.getTitle().substring(0, 2).toLowerCase(),
				this.getResponsiblePerson().substring(0, 2).toLowerCase(), this.platform.toString().toLowerCase());
	}
}
