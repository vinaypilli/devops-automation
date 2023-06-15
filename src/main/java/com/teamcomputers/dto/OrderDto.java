package com.teamcomputers.dto;

import java.time.LocalDateTime;

public class OrderDto {

	private long orderId;
	private String orderDescription;
	private long createdBy;
	private LocalDateTime createdDate;
	private long updatedBy;
	private LocalDateTime updatedDate;

	private long customerId;

	public OrderDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderDto(long orderId, String orderDescription, long createdBy, LocalDateTime createdDate, long updatedBy,
			LocalDateTime updatedDate, long customerId) {
		super();
		this.orderId = orderId;
		this.orderDescription = orderDescription;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.customerId = customerId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getOrderDescription() {
		return orderDescription;
	}

	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

}
