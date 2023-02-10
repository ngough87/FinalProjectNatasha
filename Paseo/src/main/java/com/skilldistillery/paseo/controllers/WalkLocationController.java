package com.skilldistillery.paseo.controllers;

import java.util.List;

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
	public WalkLocation findById(@PathVariable int locationId, HttpServletResponse resp) {
		WalkLocation output = null;
		output = walkLocationServ.findById(locationId);
		if (output == null) {
			resp.setStatus(404);
		} else {
			resp.setHeader("Location", "http://localhost:8090/api/walkLocation/" + output.getId());
		}
		return output;
	}

	@GetMapping("walkLocation/user/{userId}")
	public WalkLocation findByUserId(@PathVariable int userId, HttpServletResponse resp) {
		WalkLocation output = null;
		output = walkLocationServ.findByUser_Id(userId);
		if (output == null) {
			resp.setStatus(404);
		} else {
			resp.setHeader("Location", "http://localhost:8090/api/walkLocation/" + output.getId());
		}
		return output;
	}

	@PostMapping("walkLocation")
	public WalkLocation createLocation(@RequestBody WalkLocation createMe, HttpServletResponse resp) {
		WalkLocation output = null;
		try {
			output = walkLocationServ.createLocation(createMe);
			resp.setStatus(201);
			resp.setHeader("Location", "http://localhost:8090/api/walkLocation/" + output.getId());
		} catch (Exception e) {
			resp.setStatus(400);
		}
		return output;
	}

	@PutMapping("walkLocation/{locationId}")
	public WalkLocation updateLocation(@PathVariable int locationId, @RequestBody WalkLocation updateMe,
			HttpServletResponse resp) {
		WalkLocation output = null;
		try {
			output = walkLocationServ.updateLocation(updateMe, locationId);
			if (output != null) {
				resp.setStatus(202);
				resp.setHeader("Location", "http://localhost:8090/api/walkLocation/" + output.getId());
			} else {
				resp.setStatus(404);
			}
		} catch (Exception e) {
			resp.setStatus(400);
		}
		return output;
	}

	@DeleteMapping("walkLocation/{locationId}")
	public void deleteLocation(@PathVariable int locationId, HttpServletResponse resp) {
		boolean success = false;
		WalkLocation deleteMe = null;
		deleteMe = walkLocationServ.findById(locationId);
		if (deleteMe != null) {
			success = walkLocationServ.destroy(deleteMe);
			if (success) {
				resp.setStatus(204);
			} else {
				resp.setStatus(400);
			}
		} else {
			resp.setStatus(404);
		}
	}
}
