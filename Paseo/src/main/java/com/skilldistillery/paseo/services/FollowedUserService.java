package com.skilldistillery.paseo.services;

import java.util.List;

import com.skilldistillery.paseo.entities.FollowedUser;

public interface FollowedUserService {
	
	
	
	FollowedUser findById(int id);
	FollowedUser create(FollowedUser followedUser, int id);
	boolean disabledFollowedUser (int id);

}
