package com.skilldistillery.paseo.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class Walk {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private LocalDate date;
	
	private String description;
	
	@JoinColumn(name = "walk_category_id")
	private WalkCategory walkCategory;
	
	@JoinColumn(name = "walk_type_id")
	private WalkType walkType;
	
	@JoinColumn(name = "location_id")
	private WalkLocation walkLocation;
	
	@JoinColumn(name = "user_id")
	private User user;

	private Boolean privacy;
	 
	@Column(name = "start_time")
	private LocalTime startTime;

	@Column(name = "end_time")
	private LocalTime endTime;
	
	@Column(name = "main_image_url")
	private String mainImgUrl;
	
	private Boolean enabled;

	public Walk() {
		super();
	}

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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public WalkCategory getWalkCategory() {
		return walkCategory;
	}

	public void setWalkCategory(WalkCategory walkCategory) {
		this.walkCategory = walkCategory;
	}

	public WalkType getWalkType() {
		return walkType;
	}

	public void setWalkType(WalkType walkType) {
		this.walkType = walkType;
	}

	public WalkLocation getWalkLocation() {
		return walkLocation;
	}

	public void setWalkLocation(WalkLocation walkLocation) {
		this.walkLocation = walkLocation;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getPrivacy() {
		return privacy;
	}

	public void setPrivacy(Boolean privacy) {
		this.privacy = privacy;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public String getMainImgUrl() {
		return mainImgUrl;
	}

	public void setMainImgUrl(String mainImgUrl) {
		this.mainImgUrl = mainImgUrl;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
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
		Walk other = (Walk) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Walk [id=" + id + ", name=" + name + ", date=" + date + ", description=" + description
				+ ", walkCategory=" + walkCategory + ", walkType=" + walkType + ", walkLocation=" + walkLocation
				+ ", user=" + user + ", privacy=" + privacy + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", mainImgUrl=" + mainImgUrl + ", enabled=" + enabled + "]";
	}
	
	
}
