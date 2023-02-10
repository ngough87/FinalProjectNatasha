package com.skilldistillery.paseo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.paseo.entities.PreferredWalkLocation;
import com.skilldistillery.paseo.entities.PreferredWalkLocationKey;

public interface PreferredWalkLocationRepository extends JpaRepository<PreferredWalkLocation, PreferredWalkLocationKey>{

}
