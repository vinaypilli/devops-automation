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

import com.teamcomputers.dto.DepartmentDto;
import com.teamcomputers.dto.DepartmentfilterDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.message.ErrorMessage;
import com.teamcomputers.message.ResponseMessage;
import com.teamcomputers.model.DepartmentEntity;
import com.teamcomputers.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentEntityController {

	
	@Autowired
	private DepartmentService departmentService;
	
	
	String message = "";

	@PostMapping
	public ResponseEntity<ResponseMessage> add(@RequestBody DepartmentDto departmentDto) {

		try {

			this.departmentService.add(departmentDto);
			message = "Department Details successfully saved !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}

		catch (Exception e) {
			message = "Department details not saved";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	@GetMapping("/{departmentId}")
	private DepartmentEntity getById(@PathVariable int departmentId) {
		return departmentService.getById(departmentId);
	}

	@GetMapping
	private List<DepartmentEntity> getAll() {
		return departmentService.getAll();
	}

	@PutMapping("/{departmentId}")
	private ResponseEntity<ResponseMessage> update(@PathVariable int departmentId,
			@RequestBody DepartmentDto departmentDto) {

		String message = "";

		try {
			departmentDto.setDepartmentId(departmentId);
			this.departmentService.update(departmentDto);
			message = "Department Details successfully Updated !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Department details are not updated";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}
	
	
	@GetMapping("/active")
    public List<DepartmentfilterDto> getActiveUsers() {
        return departmentService.getActiveUsers();
    }

	@DeleteMapping("/{departmentId}")
	private ResponseEntity<ResponseMessage> delete(@PathVariable int departmentId) {
		try {
			departmentService.deleteById(departmentId);
			message = "Department Details successfully deleted !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Department details are not deleted" + e.getCause().getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorMessage> handleLocation(ResourceNotFoundException rx) {
		ErrorMessage errorMessage = new ErrorMessage("Department NOT FOUND", rx.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	}
}
