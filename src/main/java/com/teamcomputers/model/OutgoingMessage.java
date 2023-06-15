package com.teamcomputers.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class OutgoingMessage {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "odId")
    private List<MessageData> messageData;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<MessageData> getMessage() {
		return messageData;
	}

	public void setMessage(List<MessageData> messageData) {
		this.messageData = messageData;
	}

 
 
}