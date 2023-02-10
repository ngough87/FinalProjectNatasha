package com.skilldistillery.paseo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.paseo.entities.PreferredWalkCategory;
import com.skilldistillery.paseo.entities.PreferredWalkCategoryKey;

public interface PreferredWalkCategoryRepository extends JpaRepository<PreferredWalkCategory, PreferredWalkCategoryKey> {

}
