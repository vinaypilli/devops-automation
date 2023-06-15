package com.teamcomputers.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "order_table")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long orderId;
	@Column(nullable = false)
	private String orderDescription;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "createdBy", referencedColumnName = "userId", nullable = false, updatable = false)
	@JsonIgnoreProperties({ "email", "contact", "address", "state", "city", "postcode", "department", "role",
			"createdBy", "createdDate", "updatedBy", "updatedDate", "status", "category", "subCategory",
			"serviceTitle" })
	private UserDao createdBy;
	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime createdDate;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "updatedBy", referencedColumnName = "userId")
	@JsonIgnoreProperties({ "email", "contact", "address", "state", "city", "postcode", "department", "role",
			"createdBy", "createdDate", "updatedBy", "updatedDate", "status", "category", "subCategory",
			"serviceTitle" })
	private UserDao updatedBy;
	@UpdateTimestamp
	private LocalDateTime updatedDate;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id", referencedColumnName = "customerId")
	@JsonIgnoreProperties({ "createdBy", "createdDate", "updatedBy", "updatedDate", "email", "contact", "address",
			"state", "city", "postcode", "status" })
	private Customer customer;

	@OneToMany(mappedBy = "orders")
	@JsonIgnoreProperties({ "orderProduct" })
	private List<Order_Product> orderProduct;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(long orderId, String orderDescription, UserDao createdBy, LocalDateTime createdDate, UserDao updatedBy,
			LocalDateTime updatedDate, Customer customer, List<Order_Product> orderProduct) {
		super();
		this.orderId = orderId;
		this.orderDescription = orderDescription;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.customer = customer;
		this.orderProduct = orderProduct;
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

	public UserDao getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserDao createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public UserDao getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(UserDao updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
