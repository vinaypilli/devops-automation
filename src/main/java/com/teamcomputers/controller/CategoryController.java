package com.teamcomputers.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.teamcomputers.dto.CategoryDto;
import com.teamcomputers.dto.CategoryFilterDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.message.ErrorMessage;
import com.teamcomputers.message.ResponseMessage;
import com.teamcomputers.model.Category;
import com.teamcomputers.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	String message = "";

	@PostMapping
	public ResponseEntity<ResponseMessage> add(@RequestBody CategoryDto categoryDto) {

		try {

			this.categoryService.add(categoryDto);
			message = "Category Details successfully saved !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}

		catch (Exception e) {
			message = "Category details not saved";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	@GetMapping("/{categoryId}")
	private Category getById(@PathVariable long categoryId) {
		return categoryService.getById(categoryId);
	}

	@GetMapping("/active")
	public List<CategoryFilterDto> getActiveUsers() {
		return categoryService.getActiveUsers();
	}

	@GetMapping
	private List<Category> getAll() {
		return categoryService.getAll();
	}

	@PutMapping("/{categoryId}")
	private ResponseEntity<ResponseMessage> update(@PathVariable long categoryId,
			@RequestBody CategoryDto categoryDto) {

		String message = "";

		try {
			categoryDto.setCategoryId(categoryId);
			this.categoryService.update(categoryDto);
			message = "category Details successfully Updated !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "category details are not updated";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	@DeleteMapping("/{categoryId}")
	private ResponseEntity<ResponseMessage> delete(@PathVariable long categoryId) {
		try {
			categoryService.deleteById(categoryId);
			message = "category Details successfully deleted !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "category details are not deleted" + e.getCause().getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorMessage> handleLocation(ResourceNotFoundException rx) {
		ErrorMessage errorMessage = new ErrorMessage("CATEGORY NOT FOUND", rx.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	}

}
