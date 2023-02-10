package com.skilldistillery.paseo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.paseo.entities.Address;
import com.skilldistillery.paseo.repositories.AddressRepository;
@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	private AddressRepository addressRepo;
	
	@Override
	public List<Address> index() {
		return addressRepo.findAll();
	}
	
	@Override
	public Address create(Address address) {
		Address output = addressRepo.findByStreetAndCityAndStateAndZip(address.getStreet(), address.getCity(), address.getState(), address.getZip());
		if (output == null) {
			output = addressRepo.saveAndFlush(address);
		}
		return output;
		
	}
	@Override
	public Address findById(int id) {
		 return addressRepo.findById(id);
	}

	@Override
	public Address update(Address address, int id) {
		Address output = addressRepo.findByStreetAndCityAndStateAndZip(address.getStreet(), address.getCity(), address.getState(), address.getZip());
		if (output == null) {
			output = addressRepo.findById(id);
			
			output.setStreet(address.getStreet());
			output.setCity(address.getCity());
			output.setState(address.getState());
			output.setZip(address.getZip());
			output = addressRepo.saveAndFlush(output);
		}
		
		return output;
		
	}

}
