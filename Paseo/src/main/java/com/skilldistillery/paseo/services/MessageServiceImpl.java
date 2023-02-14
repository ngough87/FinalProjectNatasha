package com.skilldistillery.paseo.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.paseo.entities.Message;
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
		if(message != null) {
			message.setEnabled(true);
			message.setDateSent(LocalDate.now());
			messageRepo.saveAndFlush(message);
		}
		return message;
	}

	@Override
	public boolean delete(int messageId) {
		boolean success = false;
		Message deleteMe = messageRepo.findById(messageId);
		if (deleteMe != null) {
			deleteMe.setEnabled(false);
			messageRepo.saveAndFlush(deleteMe);
			success = true;
		}
		return success;
	}

	@Override
	public Message update(Message message, int id) {
		
		Message currentMessage = findById(id);
		if( message != null && currentMessage != null) {
			currentMessage.setSeen(message.isSeen());
			currentMessage = messageRepo.saveAndFlush(currentMessage);

		}
		
		return currentMessage;
	}

	@Override
	public Message findById(int messageId) {
		
		return messageRepo.findById(messageId);
	}
}
