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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.message.ErrorMessage;
import com.teamcomputers.message.ResponseMessage;
import com.teamcomputers.model.Product;
import com.teamcomputers.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	String message = "";

	@PostMapping
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,
			@RequestParam("userData") String userData) throws IOException {

		String message = "Product successfully added";

		Product product = productService.create(file, userData);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	}

	@GetMapping("/{productId}")
	private Product getById(@PathVariable long productId) {
		return productService.getById(productId);
	}

	@GetMapping
	private List<Product> getAll() {
		return productService.getAll();
	}

	@PutMapping("/{productId}")
	private ResponseEntity<ResponseMessage> uploadFile(@PathVariable long productId,
			@RequestParam("file") MultipartFile file, @RequestParam("userData") String userData) throws IOException {

		String message = "Product successfully updated";

		Product product = productService.update(productId, file, userData);
	
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

	}

	@DeleteMapping("/{productId}")
	private ResponseEntity<ResponseMessage> delete(@PathVariable long productId) {
		try {
			productService.deleteById(productId);
			message = "Product successfully deleted !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Product are not deleted" + e.getCause().getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorMessage> handleLocation(ResourceNotFoundException rx) {
		ErrorMessage errorMessage = new ErrorMessage("PRODUCT NOT FOUND", rx.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	}
}
