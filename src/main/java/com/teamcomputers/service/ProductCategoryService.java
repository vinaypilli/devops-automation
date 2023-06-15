package com.teamcomputers.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamcomputers.dto.ProductCategoryDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.model.ProductCategory;
import com.teamcomputers.repository.ProductCategoryRepository;
import com.teamcomputers.repository.UserRepository;

import javassist.NotFoundException;

@Service
public class ProductCategoryService {

	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private UserRepository userRepository;

	public String UPLOAD_DIR;

	public ProductCategory create(MultipartFile file, String userData) throws IOException {

		ProductCategoryDto productCategory = mapper.readValue(userData, ProductCategoryDto.class);
		ProductCategoryDto productCategoryDto = new ProductCategoryDto(productCategory.getProductCategoryId(),
				productCategory.getProductCategoryName(), productCategory.getProductCategoryDescription(),
				file.getBytes(), productCategory.getCreatedBy(), productCategory.getCreatedDate(),
				productCategory.getUpdatedBy(), productCategory.getUpdatedDate(), productCategory.getFilename(),
				productCategory.getFilePath(), productCategory.getFileUrl());

		String fileName = file.getOriginalFilename();

		UPLOAD_DIR = new ClassPathResource("uploads/").getFile().getAbsolutePath();

		if (file.getOriginalFilename() != null && !file.getOriginalFilename().isEmpty()) {
			Path destFilePath = Paths.get(UPLOAD_DIR, file.getOriginalFilename());
			try {
				Files.copy(file.getInputStream(), destFilePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (DirectoryNotEmptyException e) {

				throw new IOException("Cannot upload file to destination: directory is not empty.", e);
			} catch (IOException e) {

				throw new IOException("Failed to upload file to destination.", e);
			}

		}

		ProductCategory productCat = new ProductCategory();
		productCat.setProductCategoryId(productCategoryDto.getProductCategoryId());
		productCat.setProductCategoryName(productCategoryDto.getProductCategoryName());
		productCat.setProductCategoryDescription(productCategoryDto.getProductCategoryDescription());
//		productCat.setImage(productCategoryDto.getBytes());
		productCat.setCreatedBy(userRepository.findById((int) (productCategoryDto.getCreatedBy())).orElse(null));
		productCat.setCreatedDate(productCategoryDto.getCreatedDate());
		productCat.setUpdatedBy(userRepository.findById((int) (productCategoryDto.getUpdatedBy())).orElse(null));
		productCat.setUpdatedDate(productCategoryDto.getUpdatedDate());

		if (file.getOriginalFilename() != null && !file.getOriginalFilename().isEmpty()) {
			productCat.setImage(file.getBytes());
			productCat.setFileUrl(ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads/").path(fileName)
					.toUriString());
			productCat.setFilePath(UPLOAD_DIR + File.separator + fileName);
			productCat.setFilename(fileName);

		} else {

			productCat.setImage(null);
			productCat.setFileUrl(null);
			productCat.setFilePath(null);
			productCat.setFilename(null);
		}

		return productCategoryRepository.save(productCat);
	}

	public ProductCategory getById(long productCategoryId) {
		return productCategoryRepository.findById(productCategoryId).orElseThrow(
				() -> new ResourceNotFoundException("Product Category Id : " + productCategoryId + " Unavailable"));
	}

	public List<ProductCategory> getAll() {
		return productCategoryRepository.findAll();
	}

	public ProductCategory update(long productCategoryId, MultipartFile file, String userData) throws IOException {

		ProductCategoryDto productCategory = mapper.readValue(userData, ProductCategoryDto.class);
		ProductCategoryDto productCategoryDto = new ProductCategoryDto(productCategory.getProductCategoryId(),
				productCategory.getProductCategoryName(), productCategory.getProductCategoryDescription(),
				file.getBytes(), productCategory.getCreatedBy(), productCategory.getCreatedDate(),
				productCategory.getUpdatedBy(), productCategory.getUpdatedDate(), productCategory.getFilename(),
				productCategory.getFilePath(), productCategory.getFileUrl());

		String fileName = file.getOriginalFilename();

		UPLOAD_DIR = new ClassPathResource("uploads/").getFile().getAbsolutePath();

		if (file.getOriginalFilename() != null && !file.getOriginalFilename().isEmpty()) {
			Path destFilePath = Paths.get(UPLOAD_DIR, file.getOriginalFilename());
			try {
				Files.copy(file.getInputStream(), destFilePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (DirectoryNotEmptyException e) {

				throw new IOException("Cannot upload file to destination: directory is not empty.", e);
			} catch (IOException e) {

				throw new IOException("Failed to upload file to destination.", e);
			}

		}

		ProductCategory productCategory1 = new ProductCategory();
		productCategory1.setProductCategoryId(productCategoryDto.getProductCategoryId());
		productCategory1.setProductCategoryName(productCategoryDto.getProductCategoryName());
		productCategory1.setProductCategoryDescription(productCategoryDto.getProductCategoryDescription());
//		productCategory1.setImage(productCategoryDto.getBytes());
		productCategory1.setCreatedBy(userRepository.findById((int) (productCategoryDto.getCreatedBy())).orElse(null));
		productCategory1.setCreatedDate(productCategoryDto.getCreatedDate());
		productCategory1.setUpdatedBy(userRepository.findById((int) (productCategoryDto.getUpdatedBy())).orElse(null));
		productCategory1.setUpdatedDate(productCategoryDto.getUpdatedDate());

		if (file.getOriginalFilename() != null && !file.getOriginalFilename().isEmpty()) {
			productCategory1.setImage(file.getBytes());
			productCategory1.setFileUrl(ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads/")
					.path(fileName).toUriString());
			productCategory1.setFilePath(UPLOAD_DIR + File.separator + fileName);
			productCategory1.setFilename(fileName);

		} else {
			Optional<ProductCategory> fileDetails = productCategoryRepository.findById(productCategoryId);

			ProductCategory img = null;
			if (fileDetails.isPresent()) {
				img = fileDetails.get();
			}

			productCategory1.setImage(productCategory1.getImage());
			productCategory1.setFileUrl(productCategory1.getFileUrl());
			productCategory1.setFilePath(productCategory1.getFilePath());
			productCategory1.setFilename(productCategory1.getFilename());
		}

		return productCategoryRepository.save(productCategory1);
	}

	public boolean deleteById(long productCategoryId) throws NotFoundException {
		// Menu menu =
		productCategoryRepository.deleteById(productCategoryId);

//		 menuRepository.save(menu);
		return true;
	}

}
