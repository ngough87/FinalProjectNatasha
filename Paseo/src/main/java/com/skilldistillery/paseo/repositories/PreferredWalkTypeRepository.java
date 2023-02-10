package com.skilldistillery.paseo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.paseo.entities.PreferredWalkType;
import com.skilldistillery.paseo.entities.PreferredWalkTypeKey;

public interface PreferredWalkTypeRepository extends JpaRepository<PreferredWalkType, PreferredWalkTypeKey> {
}
