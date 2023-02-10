package com.skilldistillery.paseo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.paseo.entities.WalkType;

public interface WalkTypeRepository extends JpaRepository<WalkType, Integer> {
	WalkType findById(int id);
}
