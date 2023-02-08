package com.skilldistillery.paseo.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="walk_location")
public class WalkLocation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String description;
	
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address ;
	
	@Column(name = "image_url")
	private String imageUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public Address getAddressId() {
		return address;
	}

	public void setAddressId(Address addressId) {
		this.address = addressId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WalkLocation other = (WalkLocation) obj;
		return Objects.equals(address, other.address) && id == other.id;
	}

	@Override
	public String toString() {
		return "WalkLocation [id=" + id + ", name=" + name + ", description=" + description + ", addressId=" + address
				+ ", imageUrl=" + imageUrl + "]";
	}
}
