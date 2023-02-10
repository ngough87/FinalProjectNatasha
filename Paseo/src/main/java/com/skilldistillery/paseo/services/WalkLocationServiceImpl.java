package com.skilldistillery.paseo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.paseo.entities.WalkLocation;
import com.skilldistillery.paseo.repositories.WalkLocationRepository;

@Service
public class WalkLocationServiceImpl implements WalkLocationService {

	@Autowired
	private WalkLocationRepository walkLocationRepo;

	@Override
	public List<WalkLocation> index() {
		return walkLocationRepo.findAll();
	}
	
	
}
