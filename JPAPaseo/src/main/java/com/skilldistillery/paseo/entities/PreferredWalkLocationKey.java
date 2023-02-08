package com.skilldistillery.paseo.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PreferredWalkLocationKey {
	private static final long serialVersionUID = 1L;
	@Column(name="user_id")
	private int user;
	@Column(name="walk_location_id")
	private int walkLocation;
	
	public PreferredWalkLocationKey () {}

	public PreferredWalkLocationKey (int user, int walkLocation) {
		this.user = user;
		this.walkLocation = walkLocation;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public int getWalkLocation() {
		return walkLocation;
	}

	public void setWalkLocation(int walkLocation) {
		this.walkLocation = walkLocation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(user, walkLocation);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PreferredWalkLocationKey other = (PreferredWalkLocationKey) obj;
		return user == other.user && walkLocation == other.walkLocation;
	}
}
