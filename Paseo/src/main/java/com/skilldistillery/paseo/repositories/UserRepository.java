package com.skilldistillery.paseo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.paseo.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
User findByUsername(String username);

}
