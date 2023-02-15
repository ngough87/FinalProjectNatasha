package com.skilldistillery.paseo.services;

import java.util.List;

import com.skilldistillery.paseo.entities.User;
import com.skilldistillery.paseo.entities.Walk;

public interface WalkService {

	List<Walk> showWalksThatArePublic(User user);

	List<Walk> findAllWalksByUserId(int id);
	
	Walk findById(int id);

	Walk create(Walk walk, int id);
	
	Walk update(Walk walk, int id);

	boolean disableWalk(int id);

	List<Walk> searchByWalk(Walk searchWalk);
	List<Walk> getJoinedWalksByUserId(int id);

}
