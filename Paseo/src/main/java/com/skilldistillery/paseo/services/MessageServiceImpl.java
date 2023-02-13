package com.skilldistillery.paseo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.paseo.entities.Message;
import com.skilldistillery.paseo.repositories.MessageRepository;



@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageRepository messageRepo;

	@Override
	public List<Message> findAllMessagesBySender(int senderId) {
		return messageRepo.findAllBySender_Id(senderId);
	}

	@Override
	public List<Message> findAllMessagesReceived(int recieverId) {
		return messageRepo.findAllByReceiver_Id(recieverId);
	}
	






}
