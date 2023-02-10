package com.skilldistillery.paseo.services;

import com.skilldistillery.paseo.entities.User;
import com.skilldistillery.paseo.entities.WalkCategory;

public interface PreferredWalkCategoryService {

	void create(User user, WalkCategory walkCategory);

	void delete(User user, WalkCategory walkCategory);

}
