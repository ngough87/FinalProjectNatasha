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
		return walkService.show();

	}

	@GetMapping("walks/user/{userId}")
	public List<Walk> findAllByUserId(Principal principal, @PathVariable("userId") int userId,
			HttpServletResponse res) {
		System.out.println("principle inside controllers " + principal.getName());
		System.out.println("userid " + userId);
		

		if (principal != null) {
			List<Walk> userWalks = null;
			try {

				userWalks = walkService.findAllWalksByUserId(principal.getName(), userId);

				System.out.println("users list of walks: " + userWalks);
				res.setStatus(200);
				return userWalks;

			} catch (Exception e) {
				res.setStatus(405);
				e.printStackTrace();
			}
			
		}
		return null;
	}

}
