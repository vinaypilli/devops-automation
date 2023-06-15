package com.teamcomputers.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamcomputers.model.MessageData;
import com.teamcomputers.model.OutgoingMessage;
import com.teamcomputers.repository.OutgoingMessageRepository;

@Service
public class OutgoingMessageService {

	 @Autowired
	    private OutgoingMessageRepository outgoingMessageRepository;

	    public OutgoingMessage saveOutgoingMessage(OutgoingMessage outgoingMessage) {
//	
	        return outgoingMessageRepository.save(outgoingMessage);
	    }
    
//    public MessageData saveWebhookSentMessage(MessageData rp) {
//    	 return outgoingMessageRepository.save(rp);
//    
//    }
//
    public Optional<OutgoingMessage> getWebhookSentMessageById(long id) {
        return outgoingMessageRepository.findById(id) ;
    }
//
//    public void deleteWebhookSentMessage(MessageData messageData) {
//        outgoingMessageRepository.delete(messageData);
//    }

    // Add other service methods as required
}
