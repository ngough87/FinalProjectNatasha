package com.skilldistillery.paseo.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.paseo.entities.Address;
import com.skilldistillery.paseo.entities.User;
import com.skilldistillery.paseo.services.AddressService;
import com.skilldistillery.paseo.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })

public class AddressController {

@Autowired
private AddressService addressService;
@Autowired
private UserService userService;
	
@PostMapping("address/user/{UserId}")
	public Address createUserAddress(Principal principal, @PathVariable int UserId, @RequestBody Address address, HttpServletRequest req, HttpServletResponse res) {
		Address newAddress = null;
	try {
		
		User user = userService.findById(UserId);
		if(user == null) {
			res.setStatus(404);
		}
		else {
		newAddress = addressService.create(address);
		
		
		user.setAddress(newAddress);
		
		userService.update(principal.getName(), user, user.getId());
		res.setStatus(204);
		
		}
	} catch (Exception e) {
		res.setStatus(400);
		e.printStackTrace();
		
	}
	
	
		
	
		
		return newAddress;
	}

}
