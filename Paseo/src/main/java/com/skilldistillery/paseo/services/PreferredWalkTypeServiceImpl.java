package com.skilldistillery.paseo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.paseo.entities.PreferredWalkType;
import com.skilldistillery.paseo.entities.PreferredWalkTypeKey;
import com.skilldistillery.paseo.entities.User;
import com.skilldistillery.paseo.entities.WalkType;
import com.skilldistillery.paseo.repositories.PreferredWalkTypeRepository;

@Service
public class PreferredWalkTypeServiceImpl implements PreferredWalkTypeService {
	@Autowired
	private PreferredWalkTypeRepository prefTypeRepo;
	
	@Override
	public void create(User user, WalkType walkType) {
		PreferredWalkTypeKey key = new PreferredWalkTypeKey(user.getId(), walkType.getId());
		PreferredWalkType prefType = new PreferredWalkType(key, user, walkType);
		user.addPreferredWalkType(walkType);
		prefTypeRepo.saveAndFlush(prefType);
	}
	
	@Override
	public void delete(User user, WalkType walkType) {
		PreferredWalkTypeKey key = new PreferredWalkTypeKey(user.getId(), walkType.getId());
		PreferredWalkType prefType = new PreferredWalkType(key, user, walkType);
		prefTypeRepo.delete(prefType);
		user.removePreferredWalkType(walkType);
	}
}
