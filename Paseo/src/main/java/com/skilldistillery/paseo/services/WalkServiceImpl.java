package com.skilldistillery.paseo.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.paseo.entities.User;
import com.skilldistillery.paseo.entities.UserWalk;
import com.skilldistillery.paseo.entities.UserWalkKey;
import com.skilldistillery.paseo.entities.Walk;
import com.skilldistillery.paseo.repositories.UserRepository;
import com.skilldistillery.paseo.repositories.UserWalkRepository;
import com.skilldistillery.paseo.repositories.WalkRepository;

@Service
@Transactional
public class WalkServiceImpl implements WalkService {

	@Autowired
	private WalkRepository walkRepo;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserWalkRepository userWalkRepo;

	@Override
	public Walk findById(int id) {
		return walkRepo.findById(id);
	}

	// non authorized view of data below
	@Override
	public List<Walk> showWalksThatArePublic() {

		List<Walk> walks = walkRepo.findAll();

		List<Walk> copyWalkList = new ArrayList<>();

		for (Walk walk : walks) {

			if (walk.getPrivacy() == false) {
				copyWalkList.add(walk);
			}

		}
		return copyWalkList;
	}

	@Override
	public List<Walk> findAllWalksByUserId(int userID) {
		User user = null;

		List<Walk> usersListOfWalks = null;

		user = userRepo.findById(userID);

		if (user != null && user.getEnabled() == true) {

			usersListOfWalks = walkRepo.findByUser_IdAndEnabled(userID, true);
			

		}

		return usersListOfWalks;
	}

	@Override
	public Walk create(Walk newWalk, int userId) {
		User user = userRepo.findById(userId);

		if (newWalk != null && user != null) {
			newWalk.setUser(user);
			newWalk.setEnabled(true);
			newWalk = walkRepo.saveAndFlush(newWalk);
			
			UserWalkKey key = new UserWalkKey(userId, newWalk.getId());
			UserWalk joinedWalk = new UserWalk(key, user, newWalk);
			userWalkRepo.saveAndFlush(joinedWalk);
			user.addCreatedWalks(newWalk);
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
			
			UserWalkKey key = new UserWalkKey(walk.getUser().getId(), walk.getId());
			UserWalk leftWalk = new UserWalk(key, walk.getUser(), walk);
			userWalkRepo.saveAndFlush(leftWalk);
			walk.getUser().removeCreatedWalks(walk);
		}

		return deleted;
	}
	@Override
	public List <Walk> searchByWalk(Walk searchWalk){
	List<Walk> walks = walkRepo.findAll();
	List<Walk> results = new ArrayList<>();
	
	for(Walk walk:walks){
		LocalDate afterDate = walk.getDate().plusWeeks(1);
		LocalDate beforeDate = walk.getDate().minusWeeks(1);
		
		if(walk.getPrivacy() == false) {

		if (walk.getDescription().contains(searchWalk.getDescription())){
			if(!results.contains(walk)) {
			results.add(walk);
				
		}}
		if(searchWalk.getDate().isAfter(beforeDate) && searchWalk.getDate().isBefore(afterDate) && searchWalk.getDate().isAfter(LocalDate.now())) {
			if (!results.contains(walk)) {
				results.add(walk);
			}
		}
		if (walk.getWalkCategory() == searchWalk.getWalkCategory () || searchWalk.getWalkCategory().getId() == 99) {
			if (!results.contains(walk)) {
				results.add(walk);
		}
			}
		if (walk.getWalkLocation() == searchWalk.getWalkLocation() ||  searchWalk.getWalkLocation().getId() == 99){
			if (!results.contains(walk)) {
				results.add(walk);
		}
		}
		if (walk.getWalkType() == searchWalk.getWalkType() || searchWalk.getWalkType().getId() == 99) {
		
		
			if (!results.contains(walk)) {
				results.add(walk);
	}
	
		}
		
		
		} }
	return results;
}
	
	public List<Walk> getJoinedWalksByUserId(int id) {
		List<UserWalk> query = userWalkRepo.findByUserIdAndWalk_Enabled(id, true);
		List<Walk> output = new ArrayList<>();
		for (UserWalk walk : query) {
			output.add(walk.getWalk());
		}
		return output;
	}

}