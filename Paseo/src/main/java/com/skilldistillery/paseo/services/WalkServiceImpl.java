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
	public List<Walk> showWalksThatArePublic() {
		
		List<Walk> walks = walkRepo.findAll();
		
		List<Walk> copyWalkList = walks;
		
		for (Walk walk : copyWalkList) {
			
			if(walk.getPrivacy() == true) {
				return copyWalkList;
			}
			
		}
		return null;
	}


	public List<Walk> findAllWalksByUserId(int id) {
		User user = null;
		
		List<Walk> usersListOfWalks = null;

		user = userRepo.findById(id);

		if (user != null && user.getEnabled() == true ) {

			usersListOfWalks = walkRepo.findByUser_Id(id);

		}

		return usersListOfWalks;
	}
//
//	@Override
//	public Walk create(Walk walk, int userId) {
//		Optional<User> userWalk = userRepo.findById(userId);
//		System.out.println("walk: " + walk);
//		System.out.println("user id: " + userId);
//		User user = null;
//		if (userWalk.isPresent()) {
//			user = userWalk.get();
//			if (walk != null) {
//				walk.setUser(user);
//				walkRepo.saveAndFlush(walk);
//
//			}
//		}
//		return walk;
//	}



}
