package com.skilldistillery.paseo.services;

import java.util.List;

import com.skilldistillery.paseo.entities.Walk;

public interface WalkService {

	List<Walk> showWalksThatArePublic();

	List<Walk> findAllWalksByUserId(int id);
	
	Walk findById(int id);

	Walk create(Walk walk, int id);
	
	Walk update(Walk walk, int id);

	boolean disableWalk(int id);

}
