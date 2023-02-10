package com.skilldistillery.paseo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.paseo.entities.WalkLocation;

public interface WalkLocationRepository extends JpaRepository<WalkLocation, Integer>{
	WalkLocation findById(int id);
	WalkLocation findDistinctByNameAndAddress_Id(String name, int addressid);
}
