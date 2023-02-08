package com.skilldistillery.paseo.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Walk {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private LocalDate date;
	
	private String description;
	
	@Column(name = "walk_category_id")
	private int walkCatId;
	
	@Column(name = "walk_type_id")
	private int walkTypeId;
	
	@Column(name = "location_id")
	private int locationId;
	
	@Column(name = "user_id")
	private int userId;

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



	public int getWalkCatId() {
		return walkCatId;
	}



	public void setWalkCatId(int walkCatId) {
		this.walkCatId = walkCatId;
	}



	public int getWalkTypeId() {
		return walkTypeId;
	}



	public void setWalkTypeId(int walkTypeId) {
		this.walkTypeId = walkTypeId;
	}



	public int getLocationId() {
		return locationId;
	}



	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}



	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



	public boolean isPrivacy() {
		return privacy;
	}



	public void setPrivacy(boolean privacy) {
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



	public boolean isEnabled() {
		return enabled;
	}



	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}



	@Override
	public String toString() {
		return "Walk [id=" + id + ", name=" + name + ", date=" + date + ", description=" + description + ", walkCatId="
				+ walkCatId + ", walkTypeId=" + walkTypeId + ", locationId=" + locationId + ", userId=" + userId
				+ ", privacy=" + privacy + ", startTime=" + startTime + ", endTime=" + endTime + ", mainImgUrl="
				+ mainImgUrl + ", enabled=" + enabled + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(date, description, enabled, endTime, id, locationId, mainImgUrl, name, privacy, startTime,
				userId, walkCatId, walkTypeId);
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
		return Objects.equals(date, other.date) && Objects.equals(description, other.description)
				&& enabled == other.enabled && Objects.equals(endTime, other.endTime) && id == other.id
				&& locationId == other.locationId && Objects.equals(mainImgUrl, other.mainImgUrl)
				&& Objects.equals(name, other.name) && privacy == other.privacy
				&& Objects.equals(startTime, other.startTime) && userId == other.userId && walkCatId == other.walkCatId
				&& walkTypeId == other.walkTypeId;
	}






}
