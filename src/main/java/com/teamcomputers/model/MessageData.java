package com.teamcomputers.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class MessageData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long mid;

	@JsonProperty("recipient_whatsapp")
	private String recipient_whatsapp;

	@JsonProperty("recipient_type")
	private String recipient_type;

	@JsonProperty("message_type")
	private String message_type;

	@JsonProperty("source")
	private String source;

	@JsonProperty("x-apiheader")
	private String x_apiheader;
	

	@JsonProperty("type_text")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "mid")
	private List<Type_text> type_text;
	
//	@OneToMany(mappedBy = "messageData")
//	private List<OutgoingMessage> outgoingMessages;

	public String getRecipient_whatsapp() {
		return recipient_whatsapp;
	}

	public void setRecipient_whatsapp(String recipient_whatsapp) {
		this.recipient_whatsapp = recipient_whatsapp;
	}

	public String getRecipient_type() {
		return recipient_type;
	}

	public void setRecipient_type(String recipient_type) {
		this.recipient_type = recipient_type;
	}

	public String getMessage_type() {
		return message_type;
	}

	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getX_apiheader() {
		return x_apiheader;
	}

	public void setX_apiheader(String x_apiheader) {
		this.x_apiheader = x_apiheader;
	}

	public List<Type_text> getType_text() {
		return type_text;
	}

	public void setType_text(List<Type_text> type_text) {
		this.type_text = type_text;
	}

	public long getmId() {
		return mid;
	}

	public void setmId(long mid) {
		this.mid = mid;
	}

	public MessageData(long mid, String recipient_whatsapp, String recipient_type, String message_type, String source,
			String x_apiheader, List<Type_text> type_text) {
		super();
		this.mid = mid;
		this.recipient_whatsapp = recipient_whatsapp;
		this.recipient_type = recipient_type;
		this.message_type = message_type;
		this.source = source;
		this.x_apiheader = x_apiheader;
		this.type_text = type_text;
	}

	public MessageData() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	

}
