package uo.mp.myspotify.model.user;

import java.util.Objects;

import uo.mp.myspotify.service.player.Player;
import uo.mp.util.check.ArgumentChecks;

public class User {
	

	private String id;
	private Player player;

	public User(String id, Player player) {
		this(id);
		ArgumentChecks.notNull(player, "The player cannot be null");
		this.player=player;
	}

	public User(String id) {
		ArgumentChecks.notNull(id, "The id cannot be null");
		ArgumentChecks.notBlank(id, "The id cannot be blank");
		ArgumentChecks.notEmpty(id, "The id cannot be empty");
		this.id=id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	public Player getPlayer() {
		return player;
	}
	public String getId() {
		return id;
	}

	
	
}
