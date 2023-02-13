package com.skilldistillery.paseo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.paseo.entities.FollowedUser;
import com.skilldistillery.paseo.entities.FollowedUserKey;
import com.skilldistillery.paseo.entities.User;

public interface FollowedUserRepository extends JpaRepository<FollowedUser, Integer>{
	
	List<User> findByUser_Id(int id);
	
	FollowedUser findById(FollowedUserKey id);
	
}