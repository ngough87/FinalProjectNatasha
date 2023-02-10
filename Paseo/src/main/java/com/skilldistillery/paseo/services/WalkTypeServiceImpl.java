package com.skilldistillery.paseo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.paseo.entities.WalkType;
import com.skilldistillery.paseo.repositories.WalkTypeRepository;

public class WalkTypeServiceImpl implements WalkTypeService {
	
	@Autowired
	private WalkTypeRepository walkTypeRepo;

	@Override
	public List<WalkType> index() {
		return walkTypeRepo.findAll();
	}

}
