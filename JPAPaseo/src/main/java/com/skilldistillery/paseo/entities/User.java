package com.skilldistillery.paseo.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;

	private String username;
	private String password;
	private Boolean enabled;
	private String role;
	
	@OneToMany
	@JoinTable(name = "followed_user", joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "followed_user_id"))
	private List<User> followedUsers;
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
	@OneToMany
	@JoinTable(name = "user_walk", joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "walk_id"))
	private List<Walk> joinedWalks;
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
			
		}
	}
	public void removeFollower(User followedUser) {
		if (followedUsers != null && followedUsers.contains(followedUser)) {
			followedUsers.remove(followedUser);
			followedUser.removeFollower(this);
		}
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", role=" + role + ", followedUsers=" + followedUsers + ", preferredWalkCats=" + preferredWalkCats
				+ ", preferredWalkLocations=" + preferredWalkLocations + ", preferredWalkTypes=" + preferredWalkTypes
				+ ", walks=" + joinedWalks + "]";
	}

}
