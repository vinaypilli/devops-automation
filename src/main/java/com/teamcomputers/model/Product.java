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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long productId;
	@Column(nullable = false)
	private String productName;
	@Column(nullable = false)
	private String quantity;
	@Column(nullable = false)
	private String productDescription;
	@Column(nullable = false)
	private double productPrice;
	@Column(nullable = false)
	private String unitOfMeasure;
	@Lob
	@JsonIgnore
	private byte[] image;

	@JsonIgnore
	private String fileUrl;
	private String filePath;
	private String filename;

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

	@OneToMany(mappedBy = "product")
	private List<Order_Product> orderProduct;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "productCategory")
	@JsonIgnoreProperties({ "createdBy", "createdDate", "updatedBy", "updatedDate" })
	private ProductCategory productCategory;

//	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Order_Product.class)
//	@JoinColumn(name = "product_id", referencedColumnName = "productId")
//	private List<Order_Product> orderProduct;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(long productId, String productName, String quantity, String productDescription,
			double productPrice, String unitOfMeasure, byte[] image, String fileUrl, String filePath, String filename,
			UserDao createdBy, LocalDateTime createdDate, UserDao updatedBy, LocalDateTime updatedDate,
			List<Order_Product> orderProduct, ProductCategory productCategory) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.unitOfMeasure = unitOfMeasure;
		this.image = image;
		this.fileUrl = fileUrl;
		this.filePath = filePath;
		this.filename = filename;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.orderProduct = orderProduct;
		this.productCategory = productCategory;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
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

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

}
