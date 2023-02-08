package com.skilldistillery.paseo.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserWalkKey implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(name="user_id")
	private int user;
	@Column(name="walk_id")
	private int walk;
	
	public UserWalkKey () {}
	
	public UserWalkKey (int user, int walk) {
		this.user = user;
		this.walk = walk;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public int getWalk() {
		return walk;
	}

	public void setWalk(int walk) {
		this.walk = walk;
	}

	@Override
	public int hashCode() {
		return Objects.hash(user, walk);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserWalkKey other = (UserWalkKey) obj;
		return user == other.user && walk == other.walk;
	}
}
