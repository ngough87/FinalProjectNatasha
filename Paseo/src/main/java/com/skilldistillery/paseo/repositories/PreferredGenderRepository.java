package com.skilldistillery.paseo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.paseo.entities.PreferredGender;
import com.skilldistillery.paseo.entities.PreferredGenderKey;

public interface PreferredGenderRepository extends JpaRepository<PreferredGender, PreferredGenderKey>{
}
