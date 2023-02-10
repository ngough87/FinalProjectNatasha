package com.skilldistillery.paseo.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.paseo.entities.Walk;
import com.skilldistillery.paseo.services.UserService;
import com.skilldistillery.paseo.services.WalkService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })

public class WalkController {

	@Autowired
	private WalkService walkService;

	@Autowired
	private UserService userService;

	@GetMapping("walks")
	public List<Walk> show() {
		return walkService.showWalksThatArePublic();
	}
	
	@GetMapping("walks/user/{userId}")
	public List<Walk> findAllByUserId(@PathVariable("userId") int id, HttpServletResponse res) {
		 boolean enabled = true;  
		if (id > 0 && enabled) {
			List<Walk> userWalks = null;
			
			try {

				userWalks = walkService.findAllWalksByUserId(id);
				res.setStatus(200);
				return userWalks;

			} catch (Exception e) {
				res.setStatus(405);
				e.printStackTrace();
			}
			
		}
		return null;
	}

//	// Create a walk for a logged in user. Allow user to set it to public or private.
//	@PostMapping("walks/user/{walkId}")
//	public Walk create(Walk walk, int userId) {
//		boolean enabled = true;
//		Walk walk = new walk
//	}
//	
	// Update a walk based on userId. Also allow user to set it to public or private.
	
	// Allow user to "Delete" a walk. Allow user to set it to enabled or disabled. 
	
}
