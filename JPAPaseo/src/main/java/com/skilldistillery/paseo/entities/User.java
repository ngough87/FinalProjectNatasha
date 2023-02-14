package com.skilldistillery.paseo.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;

	private String username;
	

	private String password;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	private String description;
	
	@Column(name =" profile_image_url")
	private String profileImageUrl;
	
	private LocalDate birthdate;
	
	private Boolean enabled;
	
	private String role;
	
	@ManyToOne
	@JoinColumn(name="address_id")
	private Address address;
	
	@ManyToOne
	@JoinColumn(name="gender_id")
	private Gender gender;
	
	@JsonIgnore
	@OneToMany
	@JoinTable(name = "followed_user", joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "followed_user_id"))
	private List<User> followedUsers;
	
	
	@OneToMany
	@JoinTable(name = "preferred_gender", joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "gender_id"))
	private List<Gender> preferredGenders;
	

	@OneToMany
	@JoinTable(name = "preferred_walk_category", joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "walk_category_id"))
	private List<WalkCategory> preferredWalkCats;
	
	
	@OneToMany
	@JoinTable(name = "preferred_walk_location", joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "walk_location_id"))
	private List<WalkLocation> preferredWalkLocations;
	
	
	@OneToMany
	@JoinTable(name = "preferred_walk_type", joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "walk_type_id"))
	private List<WalkType> preferredWalkTypes;
	
	@JsonIgnore
	@OneToMany
	@JoinTable(name = "user_walk", joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "walk_id"))
	private List<Walk> joinedWalks;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Walk> createdWalks;

	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<User> getFollowedUsers() {
		return followedUsers;
	}

	public void setFollowedUsers(List<User> followedUsers) {
		this.followedUsers = followedUsers;
	}

	public List<WalkCategory> getPreferredWalkCats() {
		return preferredWalkCats;
	}

	public void setPreferredWalkCats(List<WalkCategory> preferredWalkCats) {
		this.preferredWalkCats = preferredWalkCats;
	}

	public List<WalkLocation> getPreferredWalkLocations() {
		return preferredWalkLocations;
	}

	public void setPreferredWalkLocations(List<WalkLocation> preferredWalkLocations) {
		this.preferredWalkLocations = preferredWalkLocations;
	}

	public List<WalkType> getPreferredWalkTypes() {
		return preferredWalkTypes;
	}

	public void setPreferredWalkTypes(List<WalkType> preferredWalkTypes) {
		this.preferredWalkTypes = preferredWalkTypes;
	}

	public List<Walk> getJoinedWalks() {
		return joinedWalks;
	}

	public void setJoinedWalks(List<Walk> joinedWalks) {
		this.joinedWalks = joinedWalks;
	}

	public List<Walk> getCreatedWalks() {
		return createdWalks;
	}

	public void setCreatedWalks(List<Walk> createdWalks) {
		this.createdWalks = createdWalks;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public List<Gender> getPreferredGenders() {
		return preferredGenders;
	}

	public void setPreferredGenders(List<Gender> preferredGenders) {
		this.preferredGenders = preferredGenders;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
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
		User other = (User) obj;
		return id == other.id;
	}
	
	
	public void addFollower(User followedUser) {
		if (followedUsers == null) {
			followedUsers = new ArrayList<>();
		}
		if (!followedUsers.contains(followedUser)) {
			followedUsers.add(followedUser);
			followedUser.addFollower(this);
		}
	}
	public void removeFollower(User followedUser) {
		if (followedUsers != null && followedUsers.contains(followedUser)) {
			followedUsers.remove(followedUser);
			followedUser.removeFollower(this);
		}
	}
	
	public void addPreferredGender(Gender gender) {
		if (preferredGenders == null) {
			preferredGenders = new ArrayList<>();
		}
		if (!preferredGenders.contains(gender)) {
			preferredGenders.add(gender);
		}
	}

	public void removePreferredGender(Gender gender) {
		if (preferredGenders != null && preferredGenders.contains(gender)) {
			preferredGenders.remove(gender);
		}
	}
	
	public void addPreferredWalkType(WalkType walkType) {
		if (preferredWalkTypes == null) {
			preferredWalkTypes = new ArrayList<>();
		}
		if (!preferredWalkTypes.contains(walkType)) {
			preferredWalkTypes.add(walkType);
		}
	}
	
	public void removePreferredWalkType(WalkType walkType) {
		if (preferredWalkTypes != null && preferredWalkTypes.contains(walkType)) {
			preferredWalkTypes.remove(walkType);
		}
	}
	
	public void addPreferredWalkCategories(WalkCategory walkCategory) {
		if (preferredWalkCats == null) {
			preferredWalkCats = new ArrayList<>();
		}
		if (!preferredWalkCats.contains(walkCategory)) {
			preferredWalkCats.add(walkCategory);
		}
	}
	
	public void removePreferredWalkCategories(WalkCategory walkCategory) {
		if (preferredWalkCats != null && preferredWalkCats.contains(walkCategory)) {
			preferredWalkCats.remove(walkCategory);
		}
	}
	
	public void addPreferredWalkLocation(WalkLocation walkLocation) {
		if (preferredWalkLocations == null) {
			preferredWalkLocations = new ArrayList<>();
		}
		if (!preferredWalkLocations.contains(walkLocation)) {
			preferredWalkLocations.add(walkLocation);
		}
	}
	
	public void removePreferredWalkLocation(WalkLocation walkLocation) {
		if (preferredWalkLocations != null && preferredWalkLocations.contains(walkLocation)) {
			preferredWalkLocations.remove(walkLocation);
		}
	}
	
	public void addJoinedWalks(Walk walk) {
		if (joinedWalks == null) {
			joinedWalks = new ArrayList<>();
		}
		if (!joinedWalks.contains(walk)) {
			joinedWalks.add(walk);
		}
	}
	
	public void removeJoinedWalks(Walk walk) {
		if (joinedWalks != null && joinedWalks.contains(walk)) {
			joinedWalks.remove(walk);
		}
	}
	
	public void addCreatedWalks(Walk walk) {
		if (createdWalks == null) {
			createdWalks = new ArrayList<>();
		}
		if (!createdWalks.contains(walk)) {
			createdWalks.add(walk);
		}
	}
	
	public void removeCreatedWalks(Walk walk) {
		if (createdWalks != null && createdWalks.contains(walk)) {
			createdWalks.remove(walk);
		}
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", description=" + description + ", profileImageUrl=" + profileImageUrl
				+ ", birthdate=" + birthdate + ", enabled=" + enabled + ", role=" + role + ", address=" + address
				+ ", gender=" + gender + "]";
	}

} 
