package com.skilldistillery.paseo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.paseo.entities.PreferredWalkCategory;
import com.skilldistillery.paseo.entities.PreferredWalkCategoryKey;
import com.skilldistillery.paseo.entities.User;
import com.skilldistillery.paseo.entities.WalkCategory;
import com.skilldistillery.paseo.repositories.PreferredWalkCategoryRepository;

@Service
public class PreferredWalkCategoryServiceImpl implements PreferredWalkCategoryService {
	@Autowired
	private PreferredWalkCategoryRepository prefCategoryRepo;
	
	@Override
	public void create(User user, WalkCategory walkCategory) {
		PreferredWalkCategoryKey key = new PreferredWalkCategoryKey(user.getId(), walkCategory.getId());
		PreferredWalkCategory prefCategory = new PreferredWalkCategory(key, user, walkCategory);
		user.addPreferredWalkCategories(walkCategory);
		prefCategoryRepo.saveAndFlush(prefCategory);
	}
	
	@Override
	public void delete(User user, WalkCategory walkCategory) {
		PreferredWalkCategoryKey key = new PreferredWalkCategoryKey(user.getId(), walkCategory.getId());
		PreferredWalkCategory prefCategory = new PreferredWalkCategory(key, user, walkCategory);
		prefCategoryRepo.delete(prefCategory);
		user.removePreferredWalkCategories(walkCategory);
	}
}
