package com.skilldistillery.paseo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.paseo.entities.Address;
import com.skilldistillery.paseo.repositories.AddressRepository;
@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	private AddressRepository addressRepo;
	
	@Override
	public Address create(Address address) {
		
		return addressRepo.saveAndFlush(address);
		
	}
	@Override
	public Address findById(int id) {
		 return addressRepo.findById(id);
	
	}

	@Override
	public Address update(Address address, int id) {
		
		
		Address existing = addressRepo.findById(id);
		
		existing.setStreet(address.getStreet());
		existing.setCity(address.getCity());
		existing.setState(address.getState());
		existing.setZip(address.getZip());
		
		return addressRepo.saveAndFlush(existing);
		
	}

}
