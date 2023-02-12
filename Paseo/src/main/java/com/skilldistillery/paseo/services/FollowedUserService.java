package com.skilldistillery.paseo.services;

import java.util.List;

import com.skilldistillery.paseo.entities.FollowedUser;
import com.skilldistillery.paseo.entities.User;

public interface FollowedUserService {
	
	
	
	FollowedUser findById(int id);
	boolean disabledFollowedUser (int id);
	FollowedUser create(int followedUserId, User loggedInUser);

}
