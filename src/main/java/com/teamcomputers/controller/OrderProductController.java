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

import com.teamcomputers.dto.OrderProductDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.message.ErrorMessage;
import com.teamcomputers.message.ResponseMessage;
import com.teamcomputers.model.Order_Product;
import com.teamcomputers.service.OrderProductService;

@RestController
@RequestMapping("/orderProduct")
public class OrderProductController {

	@Autowired
	private OrderProductService orderProductService;

	String message = "";

	@PostMapping
	public ResponseEntity<ResponseMessage> add(@RequestBody OrderProductDto orderProductDto) {

		try {

			this.orderProductService.add(orderProductDto);
			message = "Order Product successfully saved !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}

		catch (Exception e) {
			message = "Order Product not saved";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	@GetMapping("/{opId}")
	private Order_Product getById(@PathVariable long opId) {
		return orderProductService.getById(opId);
	}

	@GetMapping
	private List<Order_Product> getAll() {
		return orderProductService.getAll();
	}

	@PutMapping("/{opId}")
	private ResponseEntity<ResponseMessage> update(@PathVariable long opId,
			@RequestBody OrderProductDto orderProductDto) {

		String message = "";

		try {
			orderProductDto.setOpId(opId);
			this.orderProductService.update(orderProductDto);
			message = "Order Product successfully Updated !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Order Product are not updated";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	@DeleteMapping("/{opId}")
	private ResponseEntity<ResponseMessage> delete(@PathVariable long opId) {
		try {
			orderProductService.deleteById(opId);
			message = "Order Product successfully deleted !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Order Product are not deleted" + e.getCause().getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorMessage> handleLocation(ResourceNotFoundException rx) {
		ErrorMessage errorMessage = new ErrorMessage("ORDER NOT FOUND", rx.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	}

}
