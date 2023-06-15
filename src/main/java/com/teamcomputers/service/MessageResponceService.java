package com.teamcomputers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamcomputers.model.Data;
import com.teamcomputers.model.MessageData;
import com.teamcomputers.model.MessageResponce;
import com.teamcomputers.model.OutgoingStatus;
import com.teamcomputers.model.ResponseStatus;
import com.teamcomputers.repository.MessageResponceRepository;

@Service
public class MessageResponceService {

	@Autowired
	private MessageResponceRepository messageResponceRepository;

	public MessageResponce saveOutgoingMessage(MessageData md, OutgoingStatus os, Data data) {

		MessageResponce messageResponce = new MessageResponce();
		messageResponce.setRecipient_whatsapp(md.getRecipient_whatsapp());
		messageResponce.setRecipient_type(md.getRecipient_type());
		messageResponce.setMessage_type(md.getMessage_type());
		messageResponce.setSource(md.getSource());
		messageResponce.setX_apiheader(md.getX_apiheader());
		messageResponce.setType_text(md.getType_text());

		ResponseStatus og = new ResponseStatus();
		og.setMessage(os.getMessage());
		og.setStatus(os.getStatus());
		Data dt1 = new Data();
		dt1.setId(data.getId());
		List<Data> dt = new ArrayList<>();
		dt.add(dt1);
		og.setData(dt);
		List<ResponseStatus> rs1 = new ArrayList<>();
		rs1.add(og);

		messageResponce.setResponseStatus(rs1);
		return messageResponceRepository.save(messageResponce);
	}

	public List<MessageResponce> getAll() {
		return messageResponceRepository.findAll();
	}

}
