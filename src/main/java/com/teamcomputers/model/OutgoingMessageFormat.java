package com.teamcomputers.model;

public class OutgoingMessageFormat {
	
	private String recipient_whatsapp;
	
	private String message_type;
	
	private String content;

	public String getRecipient_whatsapp() {
		return recipient_whatsapp;
	}

	public void setRecipient_whatsapp(String recipient_whatsapp) {
		this.recipient_whatsapp = recipient_whatsapp;
	}

	public String getMessage_type() {
		return message_type;
	}

	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

}
