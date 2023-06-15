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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamcomputers.dto.ProductDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.model.Product;
import com.teamcomputers.repository.ProductCategoryRepository;
import com.teamcomputers.repository.ProductRepository;
import com.teamcomputers.repository.UserRepository;

import javassist.NotFoundException;

@Component
@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private ProductCategoryRepository productCategoryRepository;

	@Autowired
	private UserRepository userRepository;

	public String UPLOAD_DIR;

	public Product create(MultipartFile file, String userData) throws IOException {

		ProductDto products = mapper.readValue(userData, ProductDto.class);
		ProductDto productDto = new ProductDto(products.getProductId(), products.getProductName(),products.getQuantity(),
				products.getProductDescription(), products.getProductPrice(), products.getUnitOfMeasure(),
				file.getBytes(), products.getCreatedBy(), products.getCreatedDate(), products.getUpdatedBy(),
				products.getUpdatedDate(), products.getFilename(), products.getFilePath(), products.getFileUrl(),
				products.getProductCategoryId());

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

		Product product = new Product();
		product.setProductId(productDto.getProductId());
		product.setProductName(productDto.getProductName());
		product.setQuantity(productDto.getQuantity());
		product.setProductDescription(productDto.getProductDescription());
		product.setProductPrice(productDto.getProductPrice());
		product.setUnitOfMeasure(productDto.getUnitOfMeasure());
//		product.setImage(productDto.getBytes());
		product.setCreatedBy(userRepository.findById((int) (productDto.getCreatedBy())).orElse(null));
		product.setCreatedDate(productDto.getCreatedDate());
		product.setUpdatedBy(userRepository.findById((int) (productDto.getUpdatedBy())).orElse(null));
		product.setProductCategory(productCategoryRepository.findById(productDto.getProductCategoryId()).orElse(null));
		product.setUpdatedDate(productDto.getUpdatedDate());

		if (file.getOriginalFilename() != null && !file.getOriginalFilename().isEmpty()) {
			product.setImage(file.getBytes());
			product.setFileUrl(ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads/").path(fileName)
					.toUriString());
			product.setFilePath(UPLOAD_DIR + File.separator + fileName);
			product.setFilename(fileName);

		} else {

			product.setImage(null);
			product.setFileUrl(null);
			product.setFilePath(null);
			product.setFilename(null);
		}

		return productRepository.save(product);
	}

	public Product getById(long productId) {
		return productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product Id : " + productId + " Unavailable"));
	}

	public List<Product> getAll() {
		return productRepository.findAll();
	}

	public Product update(long productId, MultipartFile file, String userData) throws IOException {

		ProductDto products = mapper.readValue(userData, ProductDto.class);
		ProductDto productDto = new ProductDto(products.getProductId(), products.getProductName(),products.getQuantity(),
				products.getProductDescription(), products.getProductPrice(), products.getUnitOfMeasure(),
				file.getBytes(), products.getCreatedBy(), products.getCreatedDate(), products.getUpdatedBy(),
				products.getUpdatedDate(), products.getFilename(), products.getFilePath(), products.getFileUrl(),
				products.getProductCategoryId());

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

		Product product = new Product();
		product.setProductId(productDto.getProductId());
		product.setQuantity(productDto.getQuantity());
		product.setProductName(productDto.getProductName());
		product.setProductDescription(productDto.getProductDescription());
		product.setProductPrice(productDto.getProductPrice());
		product.setUnitOfMeasure(productDto.getUnitOfMeasure());
//		product.setImage(productDto.getBytes());
		product.setCreatedBy(userRepository.findById((int) (productDto.getCreatedBy())).orElse(null));
		product.setCreatedDate(productDto.getCreatedDate());
		product.setUpdatedBy(userRepository.findById((int) (productDto.getUpdatedBy())).orElse(null));
		product.setProductCategory(productCategoryRepository.findById(productDto.getProductCategoryId()).orElse(null));
		product.setUpdatedDate(productDto.getUpdatedDate());

		if (file.getOriginalFilename() != null && !file.getOriginalFilename().isEmpty()) {
			product.setImage(file.getBytes());
			product.setFileUrl(ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads/").path(fileName)
					.toUriString());
			product.setFilePath(UPLOAD_DIR + File.separator + fileName);
			product.setFilename(fileName);

		} else {
			Optional<Product> fileDetails = productRepository.findById(productId);

			Product med = null;
			if (fileDetails.isPresent()) {
				med = fileDetails.get();
			}

			product.setImage(product.getImage());
			product.setFileUrl(product.getFileUrl());
			product.setFilePath(product.getFilePath());
			product.setFilename(product.getFilename());
		}

		return productRepository.save(product);
	}

	public boolean deleteById(long productId) throws NotFoundException {
//		 MenuItem menuItem = 
		productRepository.deleteById(productId);

		return true;
	}

}
