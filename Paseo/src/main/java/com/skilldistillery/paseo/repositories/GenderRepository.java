package com.skilldistillery.paseo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.paseo.entities.Gender;

public interface GenderRepository extends JpaRepository<Gender, Integer> {
	
	Gender findById(int id);
	

}
