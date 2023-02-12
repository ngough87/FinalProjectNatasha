package com.skilldistillery.paseo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.paseo.entities.FollowedUser;
import com.skilldistillery.paseo.entities.Walk;

public interface FollowedUserRepository extends JpaRepository<FollowedUser, Integer>{
	
	List<Walk> findByUser_Id(int id);
	
	FollowedUser findById(int id);
	
}
