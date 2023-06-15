package com.teamcomputers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamcomputers.message.ApiResponse;
import com.teamcomputers.model.ImageType;
import com.teamcomputers.model.IncomingMessage;
import com.teamcomputers.model.LocationType;
import com.teamcomputers.model.TextType;
import com.teamcomputers.model.WebhookEntity;
import com.teamcomputers.service.IncomingMessageService;
import com.teamcomputers.service.WebhookIncomingService;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

	@Autowired
	private WebhookIncomingService webhookIncomingService;

	@PostMapping
	public ApiResponse add(@RequestBody WebhookEntity webhookEntity) {
		return webhookIncomingService.save(webhookEntity);
	}

	@GetMapping
	public List<WebhookEntity> getAllUsers() {
		return webhookIncomingService.getAll();
	}

}
