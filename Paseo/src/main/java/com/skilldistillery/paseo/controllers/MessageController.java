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
import com.skilldistillery.paseo.entities.User;
import com.skilldistillery.paseo.services.AuthService;
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
	
	@Autowired
	private AuthService authService;
	
	@GetMapping("messages")
	public List<Message> findAllSent(Principal principal,
			HttpServletResponse res) {
		
		User loggedInUser = authService.getUserByUsername(principal.getName());
			 
		return messageService.findAllMessagesBySender(loggedInUser.getId());

	}
	
	@GetMapping("messages/received")
	public List<Message> findAllReceived(Principal principal,
			@PathVariable("receiverId") int receiverId,
			HttpServletResponse res) {
		
		return messageService.findAllMessagesReceived(receiverId);
		
	}
	
	@PostMapping("user/messages/create/{senderId}")
	public Message createMessage(Principal principal,
			@PathVariable("senderId") int senderId,
			@RequestBody Message message, HttpServletResponse res) {
		return messageService.create(message, senderId);
	}
}
