package com.skilldistillery.paseo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.paseo.entities.User;
import com.skilldistillery.paseo.entities.UserWalk;
import com.skilldistillery.paseo.entities.UserWalkKey;
import com.skilldistillery.paseo.entities.Walk;
import com.skilldistillery.paseo.repositories.UserWalkRepository;

@Service
public class UserWalkServiceImpl implements UserWalkService {

	@Autowired
	private UserWalkRepository repo;

	@Override
	public void create(User user, Walk walk) {
		UserWalkKey key = new UserWalkKey(user.getId(), walk.getId());
		UserWalk joinedWalk = new UserWalk(key, user, walk);
		user.addJoinedWalks(walk);
		repo.saveAndFlush(joinedWalk);
	}

	@Override
	public void delete(User user, Walk walk) {
		UserWalkKey key = new UserWalkKey(user.getId(), walk.getId());
		UserWalk joinedWalk = new UserWalk(key, user, walk);
		repo.delete(joinedWalk);
		user.removeJoinedWalks(walk);
	}
}
