package com.skilldistillery.paseo.entities;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="preferred_gender")
public class PreferredGender {
	@EmbeddedId
	private PreferredGenderKey id;
	@ManyToOne
	@JoinColumn(name="user_id")
	@MapsId(value="user")
	private User user;
	@ManyToOne
	@JoinColumn(name="gender_id")
	@MapsId(value="gender")
	private Gender gender;
	
	public PreferredGender() {}

	public PreferredGenderKey getId() {
		return id;
	}

	public void setId(PreferredGenderKey id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
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
		PreferredGender other = (PreferredGender) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "PreferredGender [id=" + id + ", user=" + user + ", gender=" + gender + "]";
	}
	
	
}
