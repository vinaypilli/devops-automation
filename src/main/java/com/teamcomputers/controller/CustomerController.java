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

import com.teamcomputers.dto.CustomerDto;
import com.teamcomputers.dto.CustomerFilterDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.message.ErrorMessage;
import com.teamcomputers.message.ResponseMessage;
import com.teamcomputers.model.Customer;
import com.teamcomputers.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	String message = "";

	@PostMapping
	public ResponseEntity<ResponseMessage> add(@RequestBody CustomerDto customerDto) {

		try {

			this.customerService.add(customerDto);
			message = "Customer Details successfully saved !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}

//		catch (DuplicateKeyException e) {
//			//System.out.println(e + "duplivcate....");
//			message = "data is duplicate"+e.getCause().getMessage();
//			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//			} 
		catch (Exception e) {
			message = "Customer details not saved";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	@GetMapping("/{customerId}")
	private Customer getById(@PathVariable int customerId) {
		return customerService.getById(customerId);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorMessage> handleLocation(ResourceNotFoundException rx) {
		ErrorMessage errorMessage = new ErrorMessage("USER NOT FOUND", rx.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	}

	@GetMapping
	private List<Customer> getAll() {
		return customerService.getAll();
	}

	@GetMapping("/active")
	public List<CustomerFilterDto> getActiveUsers() {
		return customerService.getActiveUsers();
	}

	@PutMapping("/{customerId}")
	private ResponseEntity<ResponseMessage> update(@PathVariable int customerId, @RequestBody CustomerDto customerDto) {

		String message = "";

		try {
			customerDto.setCustomerId(customerId);
			this.customerService.update(customerDto);
			message = "Customer Details successfully Updated !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Customer details are not updated";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}
//	@PutMapping("/{customerId}")
//	private String update(@PathVariable int customerId,@RequestBody CustomerDto customerDto) {
//		customerDto.setCustomerId(customerId);
//		this.customerService.update(customerDto);
//		return "Customer Details successfully Updated !!";
//	}

	@DeleteMapping("/{customerId}")
	private ResponseEntity<ResponseMessage> delete(@PathVariable int customerId) {
		try {
			customerService.deleteById(customerId);
			message = "Customer Details successfully deleted !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Customer details are not deleted" + e.getCause().getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

}
