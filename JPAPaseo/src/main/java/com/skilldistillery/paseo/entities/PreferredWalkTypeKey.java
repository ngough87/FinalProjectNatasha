package com.skilldistillery.paseo.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PreferredWalkTypeKey implements Serializable{
	private static final long serialVersionUID = 1L;
	@Column(name="user_id")
	private int user;
	@Column(name="walk_type_id")
	private int walkType;
	
	public PreferredWalkTypeKey () {}
	
	public PreferredWalkTypeKey (int user, int walkType) {
		this.user = user;
		this.walkType = walkType;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public int getWalkType() {
		return walkType;
	}

	public void setWalkType(int walkType) {
		this.walkType = walkType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(user, walkType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PreferredWalkTypeKey other = (PreferredWalkTypeKey) obj;
		return user == other.user && walkType == other.walkType;
	}
	
}
