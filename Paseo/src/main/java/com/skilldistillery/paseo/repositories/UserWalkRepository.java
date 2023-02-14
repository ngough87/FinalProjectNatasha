package com.skilldistillery.paseo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.paseo.entities.UserWalk;
import com.skilldistillery.paseo.entities.UserWalkKey;

public interface UserWalkRepository extends JpaRepository<UserWalk, UserWalkKey>{
	List<UserWalk> findByUserId(int id);
}
