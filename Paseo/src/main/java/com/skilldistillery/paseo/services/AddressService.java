package com.skilldistillery.paseo.services;

import java.util.List;

import com.skilldistillery.paseo.entities.Address;

public interface AddressService{

	List<Address> index();
public Address create(Address address);

public Address update(Address address, int id);

Address findById(int id);
}
