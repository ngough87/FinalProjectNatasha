package com.skilldistillery.paseo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.paseo.entities.WalkLocation;
import com.skilldistillery.paseo.repositories.WalkLocationRepository;

@Service
public class WalkLocationServiceImpl implements WalkLocationService {

	@Autowired
	private WalkLocationRepository walkLocationRepo;

	@Override
	public List<WalkLocation> index() {
		return walkLocationRepo.findAll();
	}
	
	@Override
	public WalkLocation findById(int id) {
		return walkLocationRepo.findById(id);
	}

	@Override
	public WalkLocation findByUser_Id(int userId) {
		return walkLocationRepo.findByUser_Id(userId);
	}

	@Override
	public boolean destroy(WalkLocation deleteMe) {
		boolean success = false;
		try {
		walkLocationRepo.delete(deleteMe);
		success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public WalkLocation createLocation(WalkLocation createMe) {
		return walkLocationRepo.saveAndFlush(createMe);
	}

	@Override
	public WalkLocation updateLocation(WalkLocation updateMe, int locationId) {
		WalkLocation output = walkLocationRepo.findById(locationId);
		if (output != null) {
			output.setAddress(updateMe.getAddress());
			output.setDescription(updateMe.getDescription());
			output.setImageUrl(updateMe.getImageUrl());
			output.setName(updateMe.getName());
			output = walkLocationRepo.saveAndFlush(output);
		}
		return output;
	}
}
