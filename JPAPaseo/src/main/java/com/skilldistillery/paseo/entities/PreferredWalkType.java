package com.skilldistillery.paseo.entities;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="preferred_walk_type")
public class PreferredWalkType {
	@EmbeddedId
	private PreferredWalkTypeKey id;
	@ManyToOne
	@JoinColumn(name="user_id")
	@MapsId(value="user")
	private User user;
	@ManyToOne
	@JoinColumn(name="walk_type_id")
	@MapsId(value="walkType")
	private WalkType walkType;
	
	public PreferredWalkType () {}

	public PreferredWalkType(PreferredWalkTypeKey id, User user, WalkType walkType) {
		super();
		this.id = id;
		this.user = user;
		this.walkType = walkType;
	}

	public PreferredWalkTypeKey getId() {
		return id;
	}

	public void setId(PreferredWalkTypeKey id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public WalkType getWalkType() {
		return walkType;
	}

	public void setWalkType(WalkType walkType) {
		this.walkType = walkType;
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
		PreferredWalkType other = (PreferredWalkType) obj;
		return Objects.equals(id, other.id);
	}
	
}
