package com.skilldistillery.paseo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.paseo.entities.Gender;
import com.skilldistillery.paseo.entities.User;
import com.skilldistillery.paseo.entities.WalkCategory;
import com.skilldistillery.paseo.entities.WalkLocation;
import com.skilldistillery.paseo.entities.WalkType;
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
		if (existing != null) {
			existing.setUsername(user.getUsername());
			//existing.setPassword(user.getPassword());
			existing.setFirstName(user.getFirstName());
			existing.setLastName(user.getLastName());
			existing.setBirthdate(user.getBirthdate());
			existing.setProfileImageUrl(user.getProfileImageUrl());

			if(user.getAddress() == null) {
				existing.setAddress(null);
			} else {
			existing.setAddress(addRepo.findById(user.getAddress().getId()));
			}

			existing.setGender(genderRepo.findById(user.getGender().getId()));
			existing.setDescription(user.getDescription());
			existing.setPreferredGenders(user.getPreferredGenders());
			existing.setPreferredWalkCats(user.getPreferredWalkCats());
			existing.setPreferredWalkLocations(user.getPreferredWalkLocations());
			existing.setPreferredWalkTypes(user.getPreferredWalkTypes());
			
			System.out.println(existing);
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
	
	
	//non logged in users can perform action below 
	@Override
	public User findById(int id) {
		return userRepo.findById(id);
	}

	@Override
	public List<User> findUsersByMatchingPreferences(User loggedInUser) {
		List<User> output = new ArrayList<>();
		List<User> source = userRepo.findByEnabled(true);
		
		for (User query : source) {
			if (query.getPreferredGenders().size() > 0 && query.getId() != loggedInUser.getId()) {
				List<Gender> prefGenders = query.getPreferredGenders();
				List<Gender> userPrefGenders = loggedInUser.getPreferredGenders();
				for (Gender g: prefGenders) {
					if (userPrefGenders.contains(g) && !output.contains(query)) {
						output.add(query);
					}
				}
			}
			if (query.getPreferredWalkTypes().size() > 0 && query.getId() != loggedInUser.getId()) {
				List<WalkType> prefWalkTypes = query.getPreferredWalkTypes();
				List<WalkType> userPrefWalkTypes = loggedInUser.getPreferredWalkTypes();
				for (WalkType wt: prefWalkTypes) {
					if (userPrefWalkTypes.contains(wt) && !output.contains(query)) {
						output.add(query);
					}
				}
			}
			if (query.getPreferredWalkCats().size() > 0 && query.getId() != loggedInUser.getId()) {
				List<WalkCategory> prefCats = query.getPreferredWalkCats();
				List<WalkCategory> userPrefCats = loggedInUser.getPreferredWalkCats();
				for (WalkCategory cat: prefCats) {
					if (userPrefCats.contains(cat) && !output.contains(query)) {
						output.add(query);
					}
				}
			}
			if (query.getPreferredWalkLocations().size() > 0 && query.getId() != loggedInUser.getId()) {
				List<WalkLocation> prefLocations = query.getPreferredWalkLocations();
				List<WalkLocation> userPrefLocations = loggedInUser.getPreferredWalkLocations();
				for (WalkLocation location: prefLocations) {
					if (userPrefLocations.contains(location) && !output.contains(query)) {
						output.add(query);
					}
				}
			}
		}
		
		return output;
	}
	
	

}
