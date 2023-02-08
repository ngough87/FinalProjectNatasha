package com.skilldistillery.paseo.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PreferredWalkCategoryKey implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(name="walk_category_id")
	private int walkCategory;
	@Column(name="user_id")
	private int user;
	
	public PreferredWalkCategoryKey () {}
	
	public PreferredWalkCategoryKey(int walkCategory, int user) {
		this.walkCategory = walkCategory;
		this.user = user;
	}

	public int getWalkCategory() {
		return walkCategory;
	}

	public void setWalkCategory(int walkCategory) {
		this.walkCategory = walkCategory;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(user, walkCategory);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PreferredWalkCategoryKey other = (PreferredWalkCategoryKey) obj;
		return user == other.user && walkCategory == other.walkCategory;
	}

}
