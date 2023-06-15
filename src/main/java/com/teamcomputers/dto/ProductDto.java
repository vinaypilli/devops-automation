package com.teamcomputers.dto;

import java.time.LocalDateTime;

public class ProductDto {

	private long productId;
	private String productName;
	private String quantity;
	private String productDescription;
	private double productPrice;
	private String unitOfMeasure;
	private byte[] image;
	private long createdBy;
	private LocalDateTime createdDate;
	private long updatedBy;
	private LocalDateTime updatedDate;

	private String fileUrl;
	private String filePath;
	private String filename;

	private long productCategoryId;

	public ProductDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductDto(long productId, String productName, String quantity, String productDescription,
			double productPrice, String unitOfMeasure, byte[] image, long createdBy, LocalDateTime createdDate,
			long updatedBy, LocalDateTime updatedDate, String fileUrl, String filePath, String filename,
			long productCategoryId) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.unitOfMeasure = unitOfMeasure;
		this.image = image;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.fileUrl = fileUrl;
		this.filePath = filePath;
		this.filename = filename;
		this.productCategoryId = productCategoryId;
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

	public long getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(long productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

}
