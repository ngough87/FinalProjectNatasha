package com.skilldistillery.paseo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.paseo.entities.Address;
import com.skilldistillery.paseo.entities.User;
import com.skilldistillery.paseo.repositories.AddressRepository;
import com.skilldistillery.paseo.repositories.GenderRepository;
import com.skilldistillery.paseo.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private AddressRepository addRepo;
	
	@Autowired
	private GenderRepository genderRepo;
	
	

	@Override
	public List<User> show() {

		return userRepo.findAll();
	}

	@Override
	public User update(String username, User user, int id) {
		User existing = userRepo.findByUsernameAndId(username, id);
		System.out.println("existing service: " + existing);
		if (existing != null) {
			System.out.println(user + "********************************");
			existing.setUsername(user.getUsername());
			existing.setPassword(user.getPassword());
			existing.setFirstName(user.getFirstName());
			existing.setLastName(user.getLastName());
			existing.setBirthdate(user.getBirthdate());
			existing.setProfileImageUrl(user.getProfileImageUrl());
			System.out.println("inside existing: " + existing.getAddress());
			Address address = addRepo.findById(existing.getId());
			existing.setAddress(address);
			existing.setGender(genderRepo.findById(user.getGender().getId()));
			existing.setDescription(user.getDescription());

			return userRepo.saveAndFlush(existing);

		}

		return null;
	}

	@Override
	public boolean destroy(String username, int id) {
		boolean deleted = false;
		User user = userRepo.findByUsernameAndId(username, id);
		if (user != null) {
			user.setEnabled(false);
			userRepo.saveAndFlush(user);
			deleted = true;

		}
		
		return deleted;
	}
	
	@Override
	public User findById(int id) {
		return userRepo.findById(id).get();
	}

}
