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

import com.teamcomputers.dto.SubCategoryDto;
import com.teamcomputers.dto.SubCategoryFilterDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.message.ErrorMessage;
import com.teamcomputers.message.ResponseMessage;
import com.teamcomputers.model.SubCategory;
import com.teamcomputers.service.SubCategoryService;

@RestController
@RequestMapping("/subcategory")
public class SubCategoryController {

	@Autowired
	private SubCategoryService subCategoryService;

	String message = "";

	@PostMapping
	public ResponseEntity<?> add(@RequestBody SubCategoryDto subCategoryDto) {

		try {

			this.subCategoryService.add(subCategoryDto);
			message = "SubCategory Details successfully saved !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			// return ResponseEntity.ok(subCategoryService.add(subCategoryDto));

		}

//			catch (DuplicateKeyException e) {
//				//System.out.println(e + "duplicate....");
//				message = "data is duplicate"+e.getCause().getMessage();
//				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//				} 
		catch (Exception e) {
			message = "SubCategory details not saved";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	@GetMapping("/active/{category}")
	public List<SubCategoryFilterDto> getAllActiveSubcategoriesByCategoryId(@PathVariable Long category) {
		return subCategoryService.getAllActiveSubcategoriesByCategoryId(category);
	}

	@GetMapping("/{subCategoryId}")
	private SubCategory getById(@PathVariable int subCategoryId) {
		return subCategoryService.getById(subCategoryId);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorMessage> handleLocation(ResourceNotFoundException rx) {
		ErrorMessage errorMessage = new ErrorMessage("USER NOT FOUND", rx.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	}

	@GetMapping
	private List<SubCategory> getAll() {
		return subCategoryService.getAll();
	}

	@GetMapping("/active")
	public List<SubCategoryFilterDto> getActiveUsers() {
		return subCategoryService.getActiveUsers();
	}

	@PutMapping("/{subCategoryId}")
	private ResponseEntity<ResponseMessage> update(@PathVariable int subCategoryId,
			@RequestBody SubCategoryDto subCategoryDto) {

		String message = "";

		try {
			subCategoryDto.setSubCategoryId(subCategoryId);
			this.subCategoryService.update(subCategoryDto);
			message = "SubCategory Details successfully Updated !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "SubCategory details are not updated";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}
//		@PutMapping("/{subCategoryId}")
//		private String update(@PathVariable int subCategoryId,@RequestBody SubCategoryDto subCategoryDto) {
//			subCategoryDto.setSubCategoryId(subCategoryId);
//			this.subCategoryService.update(subCategoryDto);
//			return "SubCategory Details successfully Updated !!";
//		}

	@DeleteMapping("/{subCategoryId}")
	private ResponseEntity<ResponseMessage> delete(@PathVariable int subCategoryId) {
		try {
			subCategoryService.deleteById(subCategoryId);
			message = "SubCategory Details successfully deleted !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "SubCategory details are not deleted" + e.getCause().getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

}
