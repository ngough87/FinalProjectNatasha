package com.skilldistillery.paseo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.paseo.entities.Address;
import com.skilldistillery.paseo.entities.WalkLocation;
import com.skilldistillery.paseo.repositories.AddressRepository;
import com.skilldistillery.paseo.repositories.WalkLocationRepository;

@Service
public class WalkLocationServiceImpl implements WalkLocationService {

	@Autowired
	private WalkLocationRepository walkLocationRepo;
	@Autowired
	private AddressRepository addressRepo;

	@Override
	public List<WalkLocation> index() {
		return walkLocationRepo.findAll();
	}
	
	@Override
	public WalkLocation findById(int id) {
		return walkLocationRepo.findById(id);
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
		Address address = null;
		if (createMe.getAddress() != null) {
			address = addressRepo.findById(createMe.getAddress().getId());
		}
		WalkLocation output = null;
		if (address != null) {			
			createMe.setAddress(address);
		}
		output = walkLocationRepo.findDistinctByNameAndAddress_Id(createMe.getName(), createMe.getAddress().getId());
		if (output == null) {
			output = walkLocationRepo.saveAndFlush(createMe);
		}
		return output;
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
