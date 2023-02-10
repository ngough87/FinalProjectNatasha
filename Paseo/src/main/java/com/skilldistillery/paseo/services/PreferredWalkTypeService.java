package com.skilldistillery.paseo.services;

import com.skilldistillery.paseo.entities.User;
import com.skilldistillery.paseo.entities.WalkType;

public interface PreferredWalkTypeService {

	void create(User user, WalkType walkType);

	void delete(User user, WalkType walkType);

}
