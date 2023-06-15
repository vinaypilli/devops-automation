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

import com.teamcomputers.dto.RoleDto;
import com.teamcomputers.dto.RoleFilterDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.message.ErrorMessage;
import com.teamcomputers.message.ResponseMessage;
import com.teamcomputers.model.RoleEntity;
import com.teamcomputers.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	String message = "";

	@PostMapping
	public ResponseEntity<ResponseMessage> add(@RequestBody RoleDto roleDto) {

		try {
			this.roleService.add(roleDto);
			message = "Role Details successfully saved !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Role details not saved";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@PutMapping("/{roleId}")
	private ResponseEntity<ResponseMessage> update(@PathVariable int roleId, @RequestBody RoleDto roleDto) {

		String message = "";

		try {
			roleDto.setRoleId(roleId);
			this.roleService.update(roleDto);
			message = "Role Details successfully Updated !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Role details are not updated";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	@GetMapping("/{roleId}")
	private RoleEntity getById(@PathVariable int roleId) {
		return roleService.getById(roleId);
	}

	@GetMapping
	private List<RoleEntity> getAll() {
		return roleService.getAll();
	}

	@GetMapping("/active")
	public List<RoleFilterDto> getActiveUsers() {
		return roleService.getActiveUsers();
	}

	@DeleteMapping("/{customerId}")
	private ResponseEntity<ResponseMessage> delete(@PathVariable int customerId) {
		try {
			roleService.deleteById(customerId);
			message = "Role Details successfully deleted !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Role details are not deleted";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorMessage> handleLocation(ResourceNotFoundException rx) {
		ErrorMessage errorMessage = new ErrorMessage("USER NOT FOUND", rx.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	}

}
