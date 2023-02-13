package com.skilldistillery.paseo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.paseo.entities.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {
	
	List<Message> findAllBySender_Id(int senderId);
	List<Message> findAllByReceiver_Id(int receiverId);
	Message findById(int id);
}
