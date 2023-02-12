package com.skilldistillery.paseo.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.paseo.entities.FollowedUser;
import com.skilldistillery.paseo.entities.User;

import com.skilldistillery.paseo.services.AuthService;
import com.skilldistillery.paseo.services.FollowedUserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class FollowedUserController {
	
	
	@Autowired
	private FollowedUserService followedUserService;
	
	@Autowired
	private AuthService auth;
	
	
	
	@PostMapping("followedUsers")
	public FollowedUser create(@RequestBody FollowedUser followedUser, HttpServletResponse resp, HttpServletRequest req) {
		FollowedUser newFollowedUser = null;

		try {
			newFollowedUser = followedUserService.create(followedUser, followedUser.getUser().getId());
			resp.setStatus(201);

		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
		}
		return newFollowedUser;
	}
	
	
	
	@DeleteMapping("followedUsers/{id}")
	public void delete(Principal principal, @PathVariable int id, HttpServletResponse res) {
		FollowedUser deleteMe = followedUserService.findById(id);
		User loggedInUser = auth.getUserByUsername(principal.getName());
		
		if (loggedInUser.getUsername() == deleteMe.getUser().getUsername() || 
				loggedInUser.getRole().equals("ADMIN")) {
			boolean deleted = followedUserService.disabledFollowedUser(id);
		try {
			if (deleted == true) {
				res.setStatus(200);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(404);
		}
		} else {
			res.setStatus(401);
		}

	}
	

}
