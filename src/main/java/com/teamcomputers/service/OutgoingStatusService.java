package com.teamcomputers.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamcomputers.model.MessageData;
import com.teamcomputers.model.OutgoingMessage;
import com.teamcomputers.model.OutgoingStatus;
import com.teamcomputers.repository.MessageDataRepository;
import com.teamcomputers.repository.OutgoingStatusRepository;

//@Service
public class OutgoingStatusService {

//	@Autowired
//	private OutgoingStatusRepository outgoingStatusRepository;

	private MessageDataRepository messageDataRepository;

//    public OutgoingStatusService(OutgoingStatus os,OutgoingMessage om) {
//    	
//    	OutgoingStatus outgoingStatus=new OutgoingStatus();
//    	
//    	outgoingStatus.setId(os.getId());
//		
//    	outgoingStatus.setMessageData(messageDataRepository.findById(om.getId()).orElse(null));
////		UserDao user =userRepository.findById(ac.getUserId()).orElseThrow(() -> new ResourceNotFoundException("CreatedforUser Id :" + ac.getUserId()+ " Unavailable"));
//		additionalComments.setUserDao(userRepository.findById(ac.getUserId()).orElse(null));
//
//		return additionalCommentsRepository.save(additionalComments);
//    	
//   
//    }

//	public OutgoingStatus save(OutgoingStatus os, MessageData mid,String rid) {
//
//		OutgoingStatus outgoingStatus = new OutgoingStatus();
//		outgoingStatus.setMessage(os.getMessage());
//		outgoingStatus.setResponceId(rid);
//		outgoingStatus.setStatus(os.getStatus());
//		outgoingStatus.setMessageData(mid);
//
//		return outgoingStatusRepository.save(outgoingStatus);
//	}

//	public List<OutgoingStatus> getAllOutgoingStatus() {
//		return outgoingStatusRepository.findAll();
//	}
//
//	public OutgoingStatus getOutgoingStatusById(Long id) {
//		return outgoingStatusRepository.findById(id)
//				.orElseThrow(() -> new NoSuchElementException("OutgoingStatus with ID " + id + " does not exist."));
//	}

	// Add more service methods as needed

}
