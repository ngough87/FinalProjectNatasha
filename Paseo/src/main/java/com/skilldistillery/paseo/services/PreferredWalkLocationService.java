package com.skilldistillery.paseo.services;

import com.skilldistillery.paseo.entities.User;
import com.skilldistillery.paseo.entities.WalkLocation;

public interface PreferredWalkLocationService {
	void create(User user, WalkLocation location);

	void delete(User user, WalkLocation location);
}
