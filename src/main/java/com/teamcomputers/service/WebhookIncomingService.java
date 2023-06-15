package com.teamcomputers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamcomputers.message.ApiResponse;
import com.teamcomputers.model.ImageType;
import com.teamcomputers.model.IncomingMessage;
import com.teamcomputers.model.LocationType;
import com.teamcomputers.model.TextType;
import com.teamcomputers.model.WebhookEntity;
import com.teamcomputers.repository.WebhookRepository;

@Service
public class WebhookIncomingService {

	@Autowired
	private WebhookRepository webhookRepository;

	public ApiResponse save(WebhookEntity webhookEntity) {

		ApiResponse response = new ApiResponse();
		List<IncomingMessage> incomingMessages = webhookEntity.getIncoming_message();
		if (incomingMessages != null && !incomingMessages.isEmpty()) {
			IncomingMessage incomingMessage = incomingMessages.get(0); // Assuming you only want to retrieve the first
																		// incoming message
			String messageType = incomingMessage.getMessage_type();
			TextType textType = incomingMessage.getText_type();
			LocationType location_type = incomingMessage.getLocation_type();
			// String text = textType.getText();
			ImageType imageType = incomingMessage.getImage_type();

			if (messageType.equalsIgnoreCase("text")) {
				if (textType != null) {
					if (textType.getText() != null) {
						webhookRepository.save(webhookEntity);
						response.setStatus("success");
						response.setMessage("Text processed and saved successfully");
						return response;
					}
				} else {

					response.setStatus("Error");
					response.setMessage("text can't be null !!!");
					return response;
				}
			}
			if ((messageType.equalsIgnoreCase("image")) || (messageType.equalsIgnoreCase("video"))
					|| (messageType.equalsIgnoreCase("media")) || (messageType.equalsIgnoreCase("audio"))
					|| (messageType.equalsIgnoreCase("document"))) {
				if (imageType != null) {
				if ((imageType.getSha256() != null) && (imageType.getMime_type() != null)
						&& (imageType.getId() != null)) {
					webhookRepository.save(webhookEntity);
					response.setStatus("success");
					response.setMessage("media/audio/video processed and saved successfully");
					return response;
				}} else {

					response.setStatus("Error");
					response.setMessage("not getting the all file data");
					return response;
				}
			}
			if (messageType.equalsIgnoreCase("location")) {
				if(location_type!=null) {
				Double latitude = location_type.getLatitude();
				Double longitute = location_type.getLongitude();
				if ((location_type.getAddress() != null) && (location_type.getUrl() != null) && (latitude != null)
						&& (location_type.getName() != null) && (longitute != null)) {
					webhookRepository.save(webhookEntity);
					response.setStatus("success");
					response.setMessage("Location processed and saved successfully");
					return response;
				}
			} else {
				response.setStatus("Error");
				response.setMessage("location type wrong");
				return response;
			}}
		}
		response.setStatus("error");
		response.setMessage("please enter the valid message type");
		return response;
	
	}

	public List<WebhookEntity> getAll() {
		return webhookRepository.findAll();
	}
}
