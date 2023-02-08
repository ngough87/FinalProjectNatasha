package com.skilldistillery.paseo.entities;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="preferred_walk_location")
public class PreferredWalkLocation {
	@EmbeddedId
	private PreferredWalkLocationKey id;
	@ManyToOne
	@JoinColumn(name="user_id")
	@MapsId(value="user")
	private User user;
	@ManyToOne
	@JoinColumn(name="walk_location_id")
	@MapsId(value="walkLocation")
	private WalkLocation walkLocation;
	
	public PreferredWalkLocation () {}

	public PreferredWalkLocationKey getId() {
		return id;
	}

	public void setId(PreferredWalkLocationKey id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public WalkLocation getWalkLocation() {
		return walkLocation;
	}

	public void setWalkLocation(WalkLocation walkLocation) {
		this.walkLocation = walkLocation;
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
		PreferredWalkLocation other = (PreferredWalkLocation) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
