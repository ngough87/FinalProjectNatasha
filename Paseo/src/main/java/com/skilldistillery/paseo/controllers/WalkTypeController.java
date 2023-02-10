package com.skilldistillery.paseo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.paseo.entities.WalkType;
import com.skilldistillery.paseo.services.WalkTypeService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class WalkTypeController {
	
	@Autowired
	private WalkTypeService walkTypeServ;
	
	@GetMapping("walkTypes")
	public List<WalkType>index() {
		return walkTypeServ.index();
	}

}
