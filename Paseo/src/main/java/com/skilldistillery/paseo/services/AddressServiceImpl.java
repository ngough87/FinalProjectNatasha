package com.skilldistillery.paseo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.paseo.entities.Address;
import com.skilldistillery.paseo.repositories.AddressRepository;
@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	private AddressRepository addressRepo;
	
	public Address create(Address address) {
		
		return addressRepo.saveAndFlush(address);
		
	}

}
