package com.skilldistillery.paseo.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.paseo.entities.User;
import com.skilldistillery.paseo.entities.Walk;
import com.skilldistillery.paseo.repositories.AddressRepository;
import com.skilldistillery.paseo.repositories.GenderRepository;
import com.skilldistillery.paseo.repositories.UserRepository;
import com.skilldistillery.paseo.repositories.WalkRepository;

@Service
@Transactional
public class WalkServiceImpl implements WalkService {

	@Autowired
	private WalkRepository walkRepo;

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private AddressRepository addRepo;

	@Autowired
	private GenderRepository genderRepo;

	// non authorized view of data below
	@Override
	public List<Walk> show() {

		return walkRepo.findAll();
	}

	public List<Walk> findAllWalksByUserId(String username, int userId) {
		User user = null;
		List<Walk> usersListOfWalks = null;
		System.out.println( " inside impl user id: " + userId);
		System.out.println( " inside impl user name: " + username);
		
		
		  user = userRepo.findByUsername(username);
		  
		  System.out.println("user op is : " + user);
		
	

		if (user != null) {
			System.out.println("inside userOP: " + user);
			
			if (user.getUsername().equals(username)) {

				usersListOfWalks = walkRepo.findByUser_Id(userId);
				System.out.println(usersListOfWalks);
			}

		}
		return usersListOfWalks;
	}

	@Override
	public Walk create(Walk walk, int userId) {
		Optional<User> userWalk = userRepo.findById(userId);
		System.out.println("walk: " + walk);
		System.out.println("user id: " + userId);
		User user = null;
		if (userWalk.isPresent()) {
			user = userWalk.get();
			if (walk != null) {
				walk.setUser(user);
				walkRepo.saveAndFlush(walk);

			}
		}
		return walk;
	}

}
