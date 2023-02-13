package com.skilldistillery.paseo.services;

import java.util.List;

import com.skilldistillery.paseo.entities.FollowedUser;
import com.skilldistillery.paseo.entities.User;

public interface FollowedUserService {
	
	
	
	List<User> findByUserId(int id);
	FollowedUser create(int followedUserId, User loggedInUser);
	boolean disabledFollowedUser(int id, User loggedInUser);

}
