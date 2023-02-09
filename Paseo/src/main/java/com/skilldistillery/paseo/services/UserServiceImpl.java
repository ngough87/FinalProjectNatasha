package com.skilldistillery.paseo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.paseo.entities.User;
import com.skilldistillery.paseo.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<User> show() {

		return userRepo.findAll();
	}

	@Override
	public User update(String username, User user, int id) {
		User existing = userRepo.findByUsernameAndId(username, id);

		if (existing != null) {
			existing.setUsername(user.getUsername());
			existing.setPassword(user.getPassword());
			existing.setFirstName(user.getFirstName());
			existing.setLastName(user.getLastName());
			existing.setBirthdate(user.getBirthdate());
			existing.setProfileImg(user.getProfileImg());
			existing.setAddress(user.getAddress());
			existing.setGender(user.getGender());

			return userRepo.saveAndFlush(existing);

		}

		return null;
	}

	@Override
	public boolean destroy(String username, int id) {
		boolean deleted = false;
		User user = userRepo.findByUsernameAndId(username, id);
		if (user != null) {
			userRepo.delete(user);
			deleted = true;

		}
		
		return deleted;
	}
	
	@Override
	public User findById(int id) {
		return userRepo.findById(id).get();
	}

}
