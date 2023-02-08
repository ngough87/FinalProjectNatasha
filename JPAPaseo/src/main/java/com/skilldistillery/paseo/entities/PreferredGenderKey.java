package com.skilldistillery.paseo.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PreferredGenderKey implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(name="gender_id")
	private int gender;
	@Column(name="user_id")
	private int user;
	
	public PreferredGenderKey () {}
	
	public PreferredGenderKey(int gender, int user) {
		this.gender = gender;
		this.user = user;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(gender, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PreferredGenderKey other = (PreferredGenderKey) obj;
		return gender == other.gender && user == other.user;
	}
	
	

}
