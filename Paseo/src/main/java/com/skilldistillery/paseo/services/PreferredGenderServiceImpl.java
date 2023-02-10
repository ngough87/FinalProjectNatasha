package com.skilldistillery.paseo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.paseo.entities.Gender;
import com.skilldistillery.paseo.entities.PreferredGender;
import com.skilldistillery.paseo.entities.PreferredGenderKey;
import com.skilldistillery.paseo.entities.User;
import com.skilldistillery.paseo.repositories.PreferredGenderRepository;

@Service
public class PreferredGenderServiceImpl implements PreferredGenderService {
	@Autowired
	private PreferredGenderRepository prefGenderRepo;
	
	@Override
	public void create(User user, Gender gender) {
		PreferredGenderKey key = new PreferredGenderKey(user.getId(), gender.getId());
		PreferredGender prefGender = new PreferredGender(key, user, gender);
		user.addPreferredGender(gender);
		prefGenderRepo.saveAndFlush(prefGender);
	}
	
	@Override
	public void delete(User user, Gender gender) {
		PreferredGenderKey key = new PreferredGenderKey(user.getId(), gender.getId());
		PreferredGender prefGender = new PreferredGender(key, user, gender);
		prefGenderRepo.delete(prefGender);
		user.removePreferredGender(gender);
	}
}
