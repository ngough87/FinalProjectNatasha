package com.skilldistillery.paseo.entities;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String contents;
	
	@Column(name="date_sent")
	private LocalDate dateSent;
	
	private int sender;
	
	private int receiver;
	
	private boolean seen;
	
	private boolean enabled;
	
	public Message() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public LocalDate getDateSent() {
		return dateSent;
	}

	public void setDateSent(LocalDate dateSent) {
		this.dateSent = dateSent;
	}

	public int getSender() {
		return sender;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}

	public int getReceiver() {
		return receiver;
	}

	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", contents=" + contents + ", dateSent=" + dateSent + ", sender=" + sender
				+ ", receiver=" + receiver + ", seen=" + seen + ", enabled=" + enabled + "]";
	}

	



}
