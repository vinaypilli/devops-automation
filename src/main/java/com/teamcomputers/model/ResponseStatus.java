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
import com.teamcomputers.model.Data;

@Entity
public class ResponseStatus {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long responsed;

    private String status;

    private String message;
    
//    private String responceId;   
    
    @JsonProperty("data")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "osid")
	private List<Data> data;
    
//    @ManyToOne
//	@JoinColumn(name = "mId")
//	@JsonIgnoreProperties({ "recipient_whatsapp", "recipient_type","message_type","source","x-apiheader","type_text" })
//    private MessageData messageData;

	public Long getResponsed() {
		return responsed;
	}

	public void setResponsed(Long responsed) {
		this.responsed = responsed;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

//	public String getResponceId() {
//		return responceId;
//	}
//
//	public void setResponceId(String responceId) {
//		this.responceId = responceId;
//	}

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

	public ResponseStatus(Long responsed, String status, String message, List<Data> data) {
		super();
		this.responsed = responsed;
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public ResponseStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public MessageData getMessageData() {
//		return messageData;
//	}
//
//	public void setMessageData(MessageData messageData) {
//		this.messageData = messageData;
//	}
     
    

}
