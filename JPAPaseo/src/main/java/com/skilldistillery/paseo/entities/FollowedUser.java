package com.skilldistillery.paseo.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="followed_user")	
public class FollowedUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	public FollowedUser() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return id == other.id;
	}

	@Override
	public String toString() {
		return "FollowedUser [id=" + id + "]";
	}
	
	

}
