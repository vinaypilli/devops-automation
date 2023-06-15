package com.teamcomputers.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.teamcomputers.dto.ProductCategoryDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.message.ErrorMessage;
import com.teamcomputers.message.ResponseMessage;
import com.teamcomputers.model.Product;
import com.teamcomputers.model.ProductCategory;
import com.teamcomputers.service.ProductCategoryService;

@RestController
@RequestMapping("/productCategory")
public class ProductCategoryController {

   @Autowired 
	private ProductCategoryService productCategoryService;

	String message = "";

//	@PostMapping
//	public ResponseEntity<ResponseMessage> add(@RequestBody ProductCategoryDto productCategoryDto) {
//
//		try {
//
//			this.productCategoryService.add(productCategoryDto);
//			message = "Product catgegory successfully saved !!";
//			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
//		}
//
//		catch (Exception e) {
//			message = "Product catgegory not saved";
//			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//		}
//
//	}
	
	
	
	
	@PostMapping
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,
			@RequestParam("userData") String userData) throws IOException {

		String message = "Product category successfully added";

		ProductCategory productCategory1 = productCategoryService.create(file, userData);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	}
	
	

	@GetMapping("/{productCategoryId}")
	private ProductCategory getById(@PathVariable long productCategoryId) {
		return productCategoryService.getById(productCategoryId);
	}
	
	@GetMapping
	private List<ProductCategory> getAll() {
		return productCategoryService.getAll();
	}

//	@PutMapping("/{productCategoryId}")
//	private ResponseEntity<ResponseMessage> update(@PathVariable long productCategoryId,
//			@RequestBody ProductCategoryDto productCategoryDto) {
//
//		String message = "";
//
//		try {
//			productCategoryDto.setProductCategoryId(productCategoryId);
//			this.productCategoryService.update(productCategoryDto);
//			message = "Product category successfully Updated !!";
//			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
//		} catch (Exception e) {
//			message = "Product category are not updated";
//			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//		}
//
//	}
	
	
	
	@PutMapping("/{productCategoryId}")
	private ResponseEntity<ResponseMessage> uploadFile(@PathVariable long productCategoryId,
			@RequestParam("file") MultipartFile file, @RequestParam("userData") String userData) throws IOException {

		String message = "";

		ProductCategory productCategory1 = productCategoryService.update(productCategoryId, file, userData);
	
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

	}
	
	
	
	@DeleteMapping("/{productCategoryId}")
	private ResponseEntity<ResponseMessage> delete(@PathVariable long productCategoryId) {
		try {
			productCategoryService.deleteById(productCategoryId);
			message = "Product category successfully deleted !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Product category are not deleted" + e.getCause().getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorMessage> handleLocation(ResourceNotFoundException rx) {
		ErrorMessage errorMessage = new ErrorMessage("PRODUCT CATEGORY NOT FOUND", rx.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	}	
		
}
