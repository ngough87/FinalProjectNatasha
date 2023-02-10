package com.skilldistillery.paseo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.paseo.entities.WalkCategory;

public interface WalkCategoryRepository extends JpaRepository<WalkCategory, Integer>{
	WalkCategory findById(int id);
}
