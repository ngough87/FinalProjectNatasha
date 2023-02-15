package com.skilldistillery.paseo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.skilldistillery.paseo.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
User findByUsername(String username);

User findByUsernameAndId(String username, int id);

User findById(int id);

List<User> findByEnabled(boolean b);

List<User> findByFirstNameLikeOrLastNameLikeOrUsernameLike(@Param("k") String keyword, @Param("k") String keyword1, @Param("k") String keyword3);


}
