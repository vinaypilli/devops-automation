package com.teamcomputers.dto;

import java.time.LocalDateTime;

public class OrderProductDto {

	private long opId;
	private String orderProductName;
	private int orderProductQuantity;
	private double orderProductAmount;
	private long createdBy;
	private LocalDateTime createdDate;
	private long updatedBy;
	private LocalDateTime updatedDate;

	private long productId;
	private long orderId;

	public OrderProductDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderProductDto(long opId, String orderProductName, int orderProductQuantity, double orderProductAmount,
			long createdBy, LocalDateTime createdDate, long updatedBy, LocalDateTime updatedDate, long productId,
			long orderId) {
		super();
		this.opId = opId;
		this.orderProductName = orderProductName;
		this.orderProductQuantity = orderProductQuantity;
		this.orderProductAmount = orderProductAmount;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.productId = productId;
		this.orderId = orderId;
	}

	public long getOpId() {
		return opId;
	}

	public void setOpId(long opId) {
		this.opId = opId;
	}

	public String getOrderProductName() {
		return orderProductName;
	}

	public void setOrderProductName(String orderProductName) {
		this.orderProductName = orderProductName;
	}

	public int getOrderProductQuantity() {
		return orderProductQuantity;
	}

	public void setOrderProductQuantity(int orderProductQuantity) {
		this.orderProductQuantity = orderProductQuantity;
	}

	public double getOrderProductAmount() {
		return orderProductAmount;
	}

	public void setOrderProductAmount(double orderProductAmount) {
		this.orderProductAmount = orderProductAmount;
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

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

}
