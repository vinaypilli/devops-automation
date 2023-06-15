package com.teamcomputers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamcomputers.model.MessageData;
import com.teamcomputers.repository.MessageDataRepository;

@Service
public class MessageDataService {
	
	@Autowired
	private MessageDataRepository messageDataRepository;
	
	public List<MessageData> getAll() {
		return messageDataRepository.findAll();
	}
	
	public MessageData getById(long mid) {
		return messageDataRepository.findByMid(mid);
	}
	
	

}
