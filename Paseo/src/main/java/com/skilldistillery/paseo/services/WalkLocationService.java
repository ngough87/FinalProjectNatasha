package com.skilldistillery.paseo.services;

import java.util.List;

import com.skilldistillery.paseo.entities.WalkLocation;

public interface WalkLocationService {
	List<WalkLocation> index();
	WalkLocation findById(int id);
	WalkLocation findByUser_Id(int userId);
	boolean destroy(WalkLocation deleteMe);
	WalkLocation createLocation(WalkLocation createMe);
	WalkLocation updateLocation(WalkLocation updateMe, int locationId);
}
