package com.skilldistillery.paseo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.paseo.entities.Gender;
import com.skilldistillery.paseo.services.GenderService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class GenderController {

	@Autowired
	private GenderService genderService;

	@GetMapping("genders")
	public List<Gender> index() {
		return genderService.index();
	}
}
