package com.skilldistillery.paseo.entities;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "followed_user")
public class FollowedUser {
	@EmbeddedId
	private FollowedUserKey id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@MapsId(value = "user")
	private User user;

	@ManyToOne
	@JoinColumn(name = "followed_user_id")
	@MapsId(value = "followedUser")
	private User followedUser;

	public FollowedUser() {
		super();
	}

	public FollowedUserKey getId() {
		return id;
	}

	public void setId(FollowedUserKey id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getFollowedUser() {
		return followedUser;
	}

	public void setFollowedUser(User followedUser) {
		this.followedUser = followedUser;
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
		FollowedUser other = (FollowedUser) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "FollowedUser [id=" + id + ", user=" + user + ", followedUser=" + followedUser + "]";
	}

}
