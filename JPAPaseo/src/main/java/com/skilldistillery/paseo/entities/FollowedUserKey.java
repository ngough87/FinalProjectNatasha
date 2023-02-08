package com.skilldistillery.paseo.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FollowedUserKey implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(name = "followed_user_id")
	private int followedUser;
	@Column(name = "user_id")
	private int user;

	public FollowedUserKey() {
		super();
	}

	public int getFollowedUser() {
		return followedUser;
	}

	public void setFollowedUser(int followedUser) {
		this.followedUser = followedUser;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(followedUser, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FollowedUserKey other = (FollowedUserKey) obj;
		return followedUser == other.followedUser && user == other.user;
	}

	@Override
	public String toString() {
		return "FollowedUserKey [followedUser=" + followedUser + ", user=" + user + "]";
	}

}
