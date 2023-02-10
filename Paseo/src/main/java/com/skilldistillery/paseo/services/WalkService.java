package com.skilldistillery.paseo.services;

import java.util.List;

import com.skilldistillery.paseo.entities.Walk;

public interface WalkService {

	List<Walk>  showWalksThatArePublic();

	List<Walk> findAllWalksByUserId(int id);

	// Walk create(Walk walk, int userId);

}
