package com.skilldistillery.paseo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.paseo.entities.FollowedUser;
import com.skilldistillery.paseo.entities.User;
import com.skilldistillery.paseo.repositories.FollowedUserRepository;
import com.skilldistillery.paseo.repositories.UserRepository;

@Service
public class FollowedUserServiceImpl implements FollowedUserService {
	
	@Autowired
	private FollowedUserRepository followedUserRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public FollowedUser findById(int id) {
		return followedUserRepo.findById(id);
	}

	@Override
	public FollowedUser create(FollowedUser newFollowedUser, int id) {
		User follower = userRepo.findById(id);
		if(newFollowedUser != null && follower != null) {
			newFollowedUser.setUser(follower);
			followedUserRepo.saveAndFlush(newFollowedUser);
		}
	
		return null;
	}

	

	@Override
	public boolean disabledFollowedUser(int id) {
		boolean deleted = false;
		FollowedUser followedUser = followedUserRepo.findById(id);
		if (followedUser != null) {
			
			followedUserRepo.saveAndFlush(followedUser);
			deleted = true;

		}

		return deleted;
	}
	}


