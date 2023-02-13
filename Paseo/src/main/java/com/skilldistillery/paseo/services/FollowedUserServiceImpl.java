package com.skilldistillery.paseo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.paseo.entities.FollowedUser;
import com.skilldistillery.paseo.entities.FollowedUserKey;
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
	public List<User> findByUserId(int id) {
		return followedUserRepo.findByUser_Id(id);
	}

	@Override
	public FollowedUser create(int followedUserId, User loggedInUser) {
		User followedUser = userRepo.findById(followedUserId);
		FollowedUser output = null;
		
		if(followedUser != null && loggedInUser != null) {
			FollowedUserKey key = new FollowedUserKey(followedUser.getId(), loggedInUser.getId());
			FollowedUser followed = new FollowedUser(key, loggedInUser, followedUser);
			
			
			output = followedUserRepo.saveAndFlush(followed);
			loggedInUser.addFollower(followedUser);
		}
	
		return output;
	}

	

	@Override
	public boolean disabledFollowedUser(int id, User loggedInUser) {
		boolean deleted = false;
		
		User follower = userRepo.findById(id);
		FollowedUserKey key = new FollowedUserKey(id, loggedInUser.getId());
		FollowedUser followedUser = followedUserRepo.findById(key);
		
		if (followedUser != null && follower != null) {
			followedUserRepo.delete(followedUser);
			loggedInUser.removeFollower(loggedInUser);
			deleted = true;
		}

		return deleted;
	}
	}

