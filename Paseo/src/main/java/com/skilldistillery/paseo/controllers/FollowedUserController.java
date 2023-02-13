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

	@PostMapping("followedUsers/{id}")
	public void create(@PathVariable int id, Principal principal, HttpServletResponse resp, HttpServletRequest req) {
		FollowedUser newFollowedUser = null;
		User loggedInUser = auth.getUserByUsername(principal.getName());

		try {
			newFollowedUser = followedUserService.create(id, loggedInUser);
			if (newFollowedUser == null) {
				resp.setStatus(404);
			} else {
				resp.setStatus(201);
			}

		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
		}
//		return newFollowedUser;
	}

	@DeleteMapping("followedUsers/{id}")
	public void delete(Principal principal, @PathVariable int id, HttpServletResponse res) {

		User loggedInUser = auth.getUserByUsername(principal.getName());

		boolean deleted = followedUserService.disabledFollowedUser(id, loggedInUser);

		try {
			if (deleted == true) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
