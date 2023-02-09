package com.skilldistillery.paseo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.paseo.entities.Gender;
import com.skilldistillery.paseo.repositories.GenderRepository;

@Service
public class GenderServiceImpl implements GenderService {

	@Autowired
	private GenderRepository genderRepo;
	
	@Override
	public List<Gender> index() {
		return genderRepo.findAll();
		
	}

}
