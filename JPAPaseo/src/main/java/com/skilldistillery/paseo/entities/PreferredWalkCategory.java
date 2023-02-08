package com.skilldistillery.paseo.entities;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="preferred_walk_category")
public class PreferredWalkCategory {
	@EmbeddedId
	private PreferredWalkCategoryKey id;
	@ManyToOne
	@JoinColumn(name="user_id")
	@MapsId(value="user")
	private User user;
	@ManyToOne
	@JoinColumn(name="walk_category_id")
	@MapsId(value="walkCategory")
	private WalkCategory walkCategory;
	
	public PreferredWalkCategory () {}

	public PreferredWalkCategoryKey getId() {
		return id;
	}

	public void setId(PreferredWalkCategoryKey id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public WalkCategory getWalkCategory() {
		return walkCategory;
	}

	public void setWalkCategory(WalkCategory walkCategory) {
		this.walkCategory = walkCategory;
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
		PreferredWalkCategory other = (PreferredWalkCategory) obj;
		return Objects.equals(id, other.id);
	}
}
