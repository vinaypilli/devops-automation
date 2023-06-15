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

import com.teamcomputers.dto.ServiceFilterDto;
import com.teamcomputers.dto.ServicePriorityDto;
import com.teamcomputers.dto.ServiceTitleDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.message.ErrorMessage;
import com.teamcomputers.message.ResponseMessage;
import com.teamcomputers.model.ServiceTitleEntity;
import com.teamcomputers.service.ServiceTitleService;

@RestController
@RequestMapping("/servicetitle")
public class serviceEntityController {

	@Autowired
	private ServiceTitleService serviceTitleService;

	String message = "";

	@PostMapping
	public ResponseEntity<ResponseMessage> add(@RequestBody ServiceTitleDto serviceTitleDto) {

		try {

			this.serviceTitleService.add(serviceTitleDto);
			message = "Service Details successfully saved !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}

//		catch (DuplicateKeyException e) {
//			//System.out.println(e + "duplicate....");
//			message = "data is duplicate"+e.getCause().getMessage();
//			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//			} 
		catch (Exception e) {
			message = "Service details not saved";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	@GetMapping("/{serviceId}")
	private ServiceTitleEntity getById(@PathVariable int serviceId) {
		return serviceTitleService.getById(serviceId);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorMessage> handleLocation(ResourceNotFoundException rx) {
		ErrorMessage errorMessage = new ErrorMessage("USER NOT FOUND", rx.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	}

	@GetMapping("/active")
	public List<ServiceFilterDto> getActiveUsers() {
		return serviceTitleService.getActiveUsers();
	}

	@GetMapping("/active/{subCategory}")
	public List<ServiceFilterDto> getAllActiveServiceTitlesBySubCategoryId(@PathVariable int subCategory) {
		return serviceTitleService.getAllActiveServiceTitlesBySubCategoryId(subCategory);
	}

	@GetMapping
	private List<ServiceTitleEntity> getAll() {
		return serviceTitleService.getAll();
	}

	@GetMapping("/servicePriority/{serviceId}")
	public List<ServicePriorityDto> getPriorityNamesByServiceId(@PathVariable int serviceId) {
		return serviceTitleService.findPriorityNameByServiceId(serviceId);
	}

	@PutMapping("/{serviceId}")
	private ResponseEntity<ResponseMessage> update(@PathVariable int serviceId,
			@RequestBody ServiceTitleDto serviceTitleDto) {

		String message = "";

		try {
			serviceTitleDto.setServiceId(serviceId);
			this.serviceTitleService.update(serviceTitleDto);
			message = "Service Details successfully Updated !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Service details are not updated";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	@DeleteMapping("/{serviceId}")
	private ResponseEntity<ResponseMessage> delete(@PathVariable int serviceId) {
		try {
			serviceTitleService.deleteById(serviceId);
			message = "Service Details successfully deleted !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Service details are not deleted" + e.getCause().getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}
}
