package com.skilldistillery.paseo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.paseo.entities.WalkCategory;
import com.skilldistillery.paseo.repositories.WalkCategoryRepository;

public class WalkCategoryServiceImpl implements WalkCategoryService {
	
	@Autowired
	private WalkCategoryRepository walkCategoryRepo;

	@Override
	public List<WalkCategory> index() {
		return walkCategoryRepo.findAll();
	}

}
