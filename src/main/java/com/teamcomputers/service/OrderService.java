package com.teamcomputers.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teamcomputers.dto.OrderDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.model.Order;
import com.teamcomputers.repository.CustomerRepository;
import com.teamcomputers.repository.OrderRepository;
import com.teamcomputers.repository.UserRepository;
import javassist.NotFoundException;

@Service
public class OrderService {

	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Order add(OrderDto orderDto) {

		Order order = new Order();
		order.setOrderId(orderDto.getOrderId());
        order.setOrderDescription(orderDto.getOrderDescription());
		order.setCreatedBy(userRepository.findById((int)(orderDto.getCreatedBy())).orElse(null));
		order.setCreatedDate(orderDto.getCreatedDate());
		order.setUpdatedBy(userRepository.findById((int)(orderDto.getUpdatedBy())).orElse(null));
		order.setUpdatedDate(orderDto.getUpdatedDate());
		order.setCustomer(customerRepository.findById((int) orderDto.getCustomerId()).orElse(null));

		return orderRepository.save(order);
	}

	public Order getById(long orderId) {
		return orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order Id : " + orderId + " Unavailable"));
	}

	public List<Order> getAll() {
		return orderRepository.findAll();
	}

	public Order update(OrderDto orderDto) {

		Order order = new Order();
		order.setOrderId(orderDto.getOrderId());
		order.setOrderDescription(orderDto.getOrderDescription());
		order.setCreatedBy(userRepository.findById((int)(orderDto.getCreatedBy())).orElse(null));
		order.setCreatedDate(orderDto.getCreatedDate());
		order.setUpdatedBy(userRepository.findById((int)(orderDto.getUpdatedBy())).orElse(null));
		order.setUpdatedDate(orderDto.getUpdatedDate());
		order.setCustomer(customerRepository.findById((int) orderDto.getCustomerId()).orElse(null));

		return orderRepository.save(order);
	}
   
	 public boolean deleteById(long orderId) throws NotFoundException {
//		 Order order = 
				 orderRepository.findById(orderId);
//				.orElseThrow(() -> new ResourceNotFoundException("Order Id : " + orderId + " Unavailable"));
//         order.setStatus(false);
//		 orderRepository.save(order);
		return true;
	}
	
}
