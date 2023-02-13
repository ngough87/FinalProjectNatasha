package com.skilldistillery.paseo.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.paseo.entities.Message;
import com.skilldistillery.paseo.services.MessageService;
import com.skilldistillery.paseo.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class MessageController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageService messageService;
	
	@GetMapping("user/messages/sent/{senderId}")
	public List<Message> findAllSent(Principal principal,
			@PathVariable("senderId") int senderId,
			HttpServletResponse res) {
			 
		return messageService.findAllMessagesBySender(senderId);

	}
	
	@GetMapping("user/messages/received/{receiverId}")
	public List<Message> findAllReceived(Principal principal,
			@PathVariable("receiverId") int receiverId,
			HttpServletResponse res) {
		
		return messageService.findAllMessagesReceived(receiverId);
		
	}
	

}
