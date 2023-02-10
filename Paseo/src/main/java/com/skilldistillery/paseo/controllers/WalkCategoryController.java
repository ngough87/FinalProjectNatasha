package com.skilldistillery.paseo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.paseo.entities.WalkCategory;
import com.skilldistillery.paseo.services.WalkCategoryService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class WalkCategoryController {
	
	@Autowired
	private WalkCategoryService walkCategoryServ;
	
	@GetMapping("walkCategories")
	public List<WalkCategory>index() {
		return walkCategoryServ.index();
	}
}
