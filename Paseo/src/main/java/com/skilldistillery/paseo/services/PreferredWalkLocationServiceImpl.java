package com.skilldistillery.paseo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.paseo.entities.PreferredWalkLocation;
import com.skilldistillery.paseo.entities.PreferredWalkLocationKey;
import com.skilldistillery.paseo.entities.User;
import com.skilldistillery.paseo.entities.WalkLocation;
import com.skilldistillery.paseo.repositories.PreferredWalkLocationRepository;

@Service
public class PreferredWalkLocationServiceImpl implements PreferredWalkLocationService {

	@Autowired
	private PreferredWalkLocationRepository prefLocationRepo;
	
	@Override
	public void create(User user, WalkLocation location) {
		PreferredWalkLocationKey key = new PreferredWalkLocationKey(user.getId(), location.getId());
		PreferredWalkLocation prefLocation = new PreferredWalkLocation(key, user, location);
		user.addPreferredWalkLocation(location);
		prefLocationRepo.saveAndFlush(prefLocation);
	}
	
	@Override
	public void delete(User user, WalkLocation location) {
		PreferredWalkLocationKey key = new PreferredWalkLocationKey(user.getId(), location.getId());
		PreferredWalkLocation prefLocation = new PreferredWalkLocation(key, user, location);
		prefLocationRepo.delete(prefLocation);
		user.removePreferredWalkLocation(location);
	}
}
