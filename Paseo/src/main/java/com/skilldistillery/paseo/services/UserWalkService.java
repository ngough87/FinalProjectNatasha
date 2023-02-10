package com.skilldistillery.paseo.services;

import com.skilldistillery.paseo.entities.User;
import com.skilldistillery.paseo.entities.Walk;

public interface UserWalkService {

	void create(User user, Walk walk);

	void delete(User user, Walk walk);

}
