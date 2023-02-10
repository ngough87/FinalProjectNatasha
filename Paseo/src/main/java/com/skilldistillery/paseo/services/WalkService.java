package com.skilldistillery.paseo.services;

import java.util.List;

import com.skilldistillery.paseo.entities.Walk;

public interface WalkService {

	List<Walk> show();

	List<Walk> findAllWalksByUserId(String username, int userId);

	Walk create(Walk walk, int userId);

}
