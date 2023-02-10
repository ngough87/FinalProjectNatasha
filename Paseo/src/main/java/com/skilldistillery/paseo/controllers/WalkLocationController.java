package com.skilldistillery.paseo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.paseo.entities.WalkLocation;
import com.skilldistillery.paseo.services.WalkLocationService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class WalkLocationController {
	
	@Autowired
	private WalkLocationService walkLocationServ;
	
	@GetMapping("walkLocation")
	public List<WalkLocation> index() {
		return walkLocationServ.index();
	}
	
	@GetMapping("walkLocation/{locationId}")
	public WalkLocation findById(@PathVariable int locationId) {
		WalkLocation output = null;
		
		return output;
	}
	
	@PostMapping("walkLocation")
	public WalkLocation createLocation() {
		WalkLocation output = null;
		
		return output;
	}
	
	@PutMapping("walkLocation/{locationId}")
	public WalkLocation updateLocation(@PathVariable int locationId) {
		WalkLocation output = null;
		
		return output;
	}
	
	@DeleteMapping("walkLocation/{locationId}")
	public void deleteLocation(@PathVariable int locationId) {
		
	}

}
