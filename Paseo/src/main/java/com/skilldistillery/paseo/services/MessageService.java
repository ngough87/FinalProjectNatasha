package com.skilldistillery.paseo.services;

import java.util.List;

import com.skilldistillery.paseo.entities.Message;

public interface MessageService {
	Message findById(int messageId);
	List<Message> findAllMessagesBySender(int senderId);
	List<Message> findAllMessagesReceived(int recieverId);
	public Message create(Message message);
	public Message update(Message message, int id);
	boolean delete(int messageId);
	
	

}
