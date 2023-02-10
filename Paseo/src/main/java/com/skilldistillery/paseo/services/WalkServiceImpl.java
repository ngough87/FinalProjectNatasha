package com.skilldistillery.paseo.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.paseo.entities.User;
import com.skilldistillery.paseo.entities.Walk;
import com.skilldistillery.paseo.repositories.AddressRepository;
import com.skilldistillery.paseo.repositories.GenderRepository;
import com.skilldistillery.paseo.repositories.PreferredGenderRepository;
import com.skilldistillery.paseo.repositories.PreferredWalkCategoryRepository;
import com.skilldistillery.paseo.repositories.PreferredWalkLocationRepository;
import com.skilldistillery.paseo.repositories.PreferredWalkTypeRepository;
import com.skilldistillery.paseo.repositories.UserRepository;
import com.skilldistillery.paseo.repositories.WalkCategoryRepository;
import com.skilldistillery.paseo.repositories.WalkLocationRepository;
import com.skilldistillery.paseo.repositories.WalkRepository;
import com.skilldistillery.paseo.repositories.WalkTypeRepository;

@Service
@Transactional
public class WalkServiceImpl implements WalkService {

	@Autowired
	private WalkRepository walkRepo;
	
	@Autowired
	private WalkCategoryRepository walkCatRepo;
	
	@Autowired
	private WalkLocationRepository walkLocRepo;
	
	@Autowired
	private WalkTypeRepository walkTypeRepo;
	
	@Autowired
	private PreferredWalkCategoryRepository preferredWalkCatrepo;
	
	@Autowired
	private PreferredGenderRepository preferredGenderRepo;
	
	@Autowired 
	private PreferredWalkLocationRepository preferredWalkLoc;
	
	@Autowired
	private PreferredWalkTypeRepository preferredWalkTypeRepo;

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private AddressRepository addRepo;

	@Autowired
	private GenderRepository genderRepo;
	
	
	@Override
	public Walk findById(int id) {
		return walkRepo.findById(id);
	}

	// non authorized view of data below
	@Override
	public List<Walk> showWalksThatArePublic() {

		List<Walk> walks = walkRepo.findAll();

		List<Walk> copyWalkList = walks;

		for (Walk walk : copyWalkList) {

			if (walk.getPrivacy() == true) {
				return copyWalkList;
			}

		}
		return null;
	}
	@Override
	public List<Walk> findAllWalksByUserId(int userID) {
		User user = null;

		List<Walk> usersListOfWalks = null;

		user = userRepo.findById(userID);

		if (user != null && user.getEnabled() == true) {

			usersListOfWalks = walkRepo.findByUser_Id(userID);

		}

		return usersListOfWalks;
	}

	@Override
	public Walk create(Walk newWalk, int userId) {
		User user = userRepo.findById(userId);

		if (newWalk != null && user != null) {
			newWalk.setUser(user);
			newWalk.setEnabled(true);
			walkRepo.saveAndFlush(newWalk);

		}

		return newWalk;
	}

	@Override
	public Walk update(Walk walk, int id) {
		Walk updatedWalk = walkRepo.findById(id);
		if (updatedWalk != null) {
			updatedWalk.setName(walk.getName());
			updatedWalk.setDate(walk.getDate());
			updatedWalk.setWalkCategory(walk.getWalkCategory());
			updatedWalk.setWalkType(walk.getWalkType());
			updatedWalk.setWalkLocation(walk.getWalkLocation());
			updatedWalk.setUser(walk.getUser());
			updatedWalk.setPrivacy(walk.getPrivacy());
			updatedWalk.setStartTime(walk.getStartTime());
			updatedWalk.setEndTime(walk.getEndTime());
			updatedWalk.setMainImgUrl(walk.getMainImgUrl());
			updatedWalk.setEnabled(walk.getEnabled());
			updatedWalk = walkRepo.saveAndFlush(updatedWalk);
		}
		
		return updatedWalk;
		
	}

	@Override
	public boolean disableWalk(int id) {
		boolean deleted = false;
		Walk walk = walkRepo.findById(id);
		if (walk != null) {
			walk.setEnabled(false);
			walkRepo.saveAndFlush(walk);
			deleted = true;

		}
		
		return deleted;
	}
}
