package com.skilldistillery.paseo.entities;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="user_walk")
public class UserWalk {
	@EmbeddedId
	private UserWalkKey id;
	@ManyToOne
	@JoinColumn(name="user_id")
	@MapsId(value="user")
	private User user;
	@ManyToOne
	@JoinColumn(name="walk_id")
	@MapsId(value="walk")
	private Walk walk;
	private String description;
	private Integer rating;
	
	public UserWalk () {}

	public UserWalk(UserWalkKey id, User user, Walk walk) {
		super();
		this.id = id;
		this.user = user;
		this.walk = walk;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Walk getWalk() {
		return walk;
	}

	public void setWalk(Walk walk) {
		this.walk = walk;
	}

	public UserWalkKey getId() {
		return id;
	}

	public void setId(UserWalkKey id) {
		this.id = id;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		UserWalk other = (UserWalk) obj;
		return Objects.equals(id, other.id);
	}
}
