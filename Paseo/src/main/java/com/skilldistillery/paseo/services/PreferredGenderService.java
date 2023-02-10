package com.skilldistillery.paseo.services;

import com.skilldistillery.paseo.entities.Gender;
import com.skilldistillery.paseo.entities.User;

public interface PreferredGenderService {

	void create(User user, Gender gender);

	void delete(User user, Gender gender);

}
