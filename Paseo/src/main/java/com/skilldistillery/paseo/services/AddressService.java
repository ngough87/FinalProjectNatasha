package com.skilldistillery.paseo.services;

import com.skilldistillery.paseo.entities.Address;

public interface AddressService{

	
public Address create(Address address);

public Address update(Address address, int id);

Address findById(int id);
}
