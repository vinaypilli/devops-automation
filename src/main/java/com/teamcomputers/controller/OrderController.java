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
import com.teamcomputers.dto.OrderDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.message.ErrorMessage;
import com.teamcomputers.message.ResponseMessage;
import com.teamcomputers.model.Order;
import com.teamcomputers.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	
	@Autowired 
	private OrderService orderService;

	String message = "";

	@PostMapping
	public ResponseEntity<ResponseMessage> add(@RequestBody OrderDto orderDto) {

		try {

			this.orderService.add(orderDto);
			message = "Order successfully saved !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}

		catch (Exception e) {
			message = "Order not saved";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	@GetMapping("/{orderId}")
	private Order getById(@PathVariable long orderId) {
		return orderService.getById(orderId);
	}
	
	@GetMapping
	private List<Order> getAll() {
		return orderService.getAll();
	}

	@PutMapping("/{orderId}")
	private ResponseEntity<ResponseMessage> update(@PathVariable long orderId,
			@RequestBody OrderDto orderDto) {

		String message = "";

		try {
			orderDto.setOrderId(orderId);
			this.orderService.update(orderDto);
			message = "Order successfully Updated !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Order are not updated";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}
	
	@DeleteMapping("/{orderId}")
	private ResponseEntity<ResponseMessage> delete(@PathVariable long orderId) {
		try {
			orderService.deleteById(orderId);
			message = "Order successfully deleted !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Order are not deleted" + e.getCause().getMessage();
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
