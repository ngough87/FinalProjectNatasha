package com.skilldistillery.paseo.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public List<Walk> findAllByUserId(@PathVariable("userId") int id, HttpServletResponse res, HttpServletRequest req) {
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
	
	@PostMapping("walks")
	public Walk create(@RequestBody Walk walk, HttpServletResponse resp, HttpServletRequest req) {
		Walk newWalk = null;
		
		try {
			newWalk = walkService.create(walk, walk.getUser().getId());
			resp.setStatus(201);
			
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
		}
		return newWalk;
}
	@PutMapping("walks/{walkId}")
	public Walk updateWalk(Principal princial, @PathVariable int walkId, @RequestBody Walk walk,
			HttpServletRequest req, HttpServletResponse res) {

		Walk updatedWalk = null;

		try {
			updatedWalk = walkService.findById(walkId);
			if (updatedWalk == null) {
				res.setStatus(404);
			} else {
				updatedWalk = walkService.update(walk, walkId);
				res.setStatus(202);

			}
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();

		}

		return updatedWalk;
	}

	@DeleteMapping("walks/{walkId}")
	public void delete(@PathVariable int walkId, HttpServletResponse res) { 
		 boolean deleted = walkService.disableWalk(walkId);
		try {
			if(deleted == true) {
				res.setStatus(200); 
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(404);
		}
		
		
	}
}
