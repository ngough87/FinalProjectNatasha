package com.skilldistillery.paseo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.paseo.entities.Message;
import com.skilldistillery.paseo.entities.User;
import com.skilldistillery.paseo.repositories.MessageRepository;
import com.skilldistillery.paseo.repositories.UserRepository;



@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageRepository messageRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Message> findAllMessagesBySender(int senderId) {
		return messageRepo.findAllBySender_Id(senderId);
	}

	@Override
	public List<Message> findAllMessagesReceived(int recieverId) {
		return messageRepo.findAllByReceiver_Id(recieverId);
	}

	@Override
	public Message create(Message message) {
		User user = userRepo.findById(senderId);
		
		if(message != null && user != null) {
			message.setSender(user);
			message.setEnabled(true);
			messageRepo.saveAndFlush(message);
			
		}
		return message;
	}

//	@Override
//	public Message delete(Message message, int userId) {
//		return null;
//	}

	
	






}
